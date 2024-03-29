/*
 *  Copyright 1999-2019 Seata.io Group.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package io.seata.rm.datasource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.sql.DataSource;

import io.seata.common.thread.NamedThreadFactory;
import io.seata.config.ConfigurationFactory;
import io.seata.core.constants.ConfigurationKeys;
import io.seata.core.context.RootContext;
import io.seata.core.model.BranchType;
import io.seata.core.model.Resource;
import io.seata.rm.DefaultResourceManager;
import io.seata.rm.datasource.sql.struct.TableMetaCacheFactory;
import io.seata.rm.datasource.util.JdbcUtils;
import io.seata.sqlparser.util.JdbcConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.seata.common.DefaultValues.DEFAULT_CLIENT_TABLE_META_CHECK_ENABLE;

/**
 * The type Data source proxy.
 *
 * @author sharajava
 */
public class DataSourceProxy extends AbstractDataSourceProxy implements Resource {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceProxy.class);

    private static final String DEFAULT_RESOURCE_GROUP_ID = "DEFAULT";

    private String resourceGroupId;

    private String jdbcUrl;

    private String dbType;

    private String userName;

    /**
     * Enable the table meta checker
     */
    private static boolean ENABLE_TABLE_META_CHECKER_ENABLE = ConfigurationFactory.getInstance().getBoolean(
        ConfigurationKeys.CLIENT_TABLE_META_CHECK_ENABLE, DEFAULT_CLIENT_TABLE_META_CHECK_ENABLE);

    /**
     * Table meta checker interval
     */
    private static final long TABLE_META_CHECKER_INTERVAL = 60000L;

    private final ScheduledExecutorService tableMetaExcutor = new ScheduledThreadPoolExecutor(1,
        new NamedThreadFactory("tableMetaChecker", 1, true));

    /**
     * Instantiates a new Data source proxy.
     *
     * @param targetDataSource the target data source
     */
    public DataSourceProxy(DataSource targetDataSource) {
        this(targetDataSource, DEFAULT_RESOURCE_GROUP_ID);
    }

    /**
     * Instantiates a new Data source proxy.
     *
     * @param targetDataSource the target data source
     * @param resourceGroupId  the resource group id
     */
    public DataSourceProxy(DataSource targetDataSource, String resourceGroupId) {
        if (targetDataSource instanceof SeataDataSourceProxy) {
            LOGGER.info("Unwrap the target data source, because the type is: {}", targetDataSource.getClass().getName());
            targetDataSource = ((SeataDataSourceProxy) targetDataSource).getTargetDataSource();
        }
        this.targetDataSource = targetDataSource;
        init(targetDataSource, resourceGroupId);
    }

    private void init(DataSource dataSource, String resourceGroupId) {
        this.resourceGroupId = resourceGroupId;
        try (Connection connection = dataSource.getConnection()) {
            jdbcUrl = connection.getMetaData().getURL();
            dbType = JdbcUtils.getDbType(jdbcUrl);
            if (JdbcConstants.ORACLE.equals(dbType)) {
                userName = connection.getMetaData().getUserName();
            }
        } catch (SQLException e) {
            throw new IllegalStateException("can not init dataSource", e);
        }
        // this = DataSourceProxy 是 db Resource 的一种 所以将自己 register 给 DataSourceResourceManager 进行管理 分支注册 等等操作
        DefaultResourceManager.get().registerResource(this);
        // 自动刷新缓存中的表结构
        if (ENABLE_TABLE_META_CHECKER_ENABLE) {
            tableMetaExcutor.scheduleAtFixedRate(() -> {
                try (Connection connection = dataSource.getConnection()) {
                    TableMetaCacheFactory.getTableMetaCache(DataSourceProxy.this.getDbType())
                        .refresh(connection, DataSourceProxy.this.getResourceId());
                } catch (Exception ignore) {
                }
            }, 0, TABLE_META_CHECKER_INTERVAL, TimeUnit.MILLISECONDS);
        }

        //Set the default branch type to 'AT' in the RootContext.
        RootContext.setDefaultBranchType(this.getBranchType());
    }

    /**
     * Gets plain connection.
     *
     * @return the plain connection
     * @throws SQLException the sql exception
     */
    public Connection getPlainConnection() throws SQLException {
        return targetDataSource.getConnection();
    }

    /**
     * Gets db type.
     *
     * @return the db type
     */
    public String getDbType() {
        return dbType;
    }


    /**
     *   需要注意的一个问题是 在使用seata 后 由于 对数据源做了代理 在没有 处于 分支事务的时候 也会 创建代理 ？？？？？
     *   seata 默认使用的 DataSourceXA 所以这里没做处理
     */
    @Override
    public ConnectionProxy getConnection() throws SQLException {
        Connection targetConnection = targetDataSource.getConnection();
        return new ConnectionProxy(this, targetConnection);
    }

    @Override
    public ConnectionProxy getConnection(String username, String password) throws SQLException {
        Connection targetConnection = targetDataSource.getConnection(username, password);
        return new ConnectionProxy(this, targetConnection);
    }

    @Override
    public String getResourceGroupId() {
        return resourceGroupId;
    }


    @Override
    public String getResourceId() {
        if (JdbcConstants.POSTGRESQL.equals(dbType)) {
            return getPGResourceId();
        } else if (JdbcConstants.ORACLE.equals(dbType) && userName != null) {
            return getDefaultResourceId() + "/" + userName;
        } else {
            return getDefaultResourceId();
        }
    }

    /**
     * get the default resource id
     * @return resource id
     */
    private String getDefaultResourceId() {
        if (jdbcUrl.contains("?")) {
            return jdbcUrl.substring(0, jdbcUrl.indexOf('?'));
        } else {
            return jdbcUrl;
        }
    }

    /**
     * prevent pg sql url like
     * jdbc:postgresql://127.0.0.1:5432/seata?currentSchema=public
     * jdbc:postgresql://127.0.0.1:5432/seata?currentSchema=seata
     * cause the duplicated resourceId
     * it will cause the problem like
     * 1.get file lock fail
     * 2.error table meta cache
     * @return resourceId
     */
    private String getPGResourceId() {
        if (jdbcUrl.contains("?")) {
            StringBuilder jdbcUrlBuilder = new StringBuilder();
            jdbcUrlBuilder.append(jdbcUrl.substring(0, jdbcUrl.indexOf('?')));
            StringBuilder paramsBuilder = new StringBuilder();
            String paramUrl = jdbcUrl.substring(jdbcUrl.indexOf('?') + 1, jdbcUrl.length());
            String[] urlParams = paramUrl.split("&");
            for (String urlParam : urlParams) {
                if (urlParam.contains("currentSchema")) {
                    paramsBuilder.append(urlParam);
                    break;
                }
            }

            if (paramsBuilder.length() > 0) {
                jdbcUrlBuilder.append("?");
                jdbcUrlBuilder.append(paramsBuilder);
            }
            return jdbcUrlBuilder.toString();
        } else {
            return jdbcUrl;
        }
    }

    @Override
    public BranchType getBranchType() {
        return BranchType.AT;
    }
}
