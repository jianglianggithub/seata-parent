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
package io.seata.rm.datasource.exec;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import io.seata.common.util.IOUtil;
import io.seata.common.util.StringUtils;
import io.seata.config.Configuration;
import io.seata.config.ConfigurationFactory;
import io.seata.core.constants.ConfigurationKeys;
import io.seata.common.DefaultValues;
import io.seata.rm.datasource.ColumnUtils;
import io.seata.rm.datasource.SqlGenerateUtils;
import io.seata.rm.datasource.StatementProxy;
import io.seata.rm.datasource.sql.struct.TableMeta;
import io.seata.rm.datasource.sql.struct.TableRecords;
import io.seata.sqlparser.ParametersHolder;
import io.seata.sqlparser.SQLRecognizer;
import io.seata.sqlparser.SQLUpdateRecognizer;

/**
 * The type Update executor.
 *
 * @param <T> the type parameter
 * @param <S> the type parameter
 * @author sharajava
 */
public class UpdateExecutor<T, S extends Statement> extends AbstractDMLBaseExecutor<T, S> {

    private static final Configuration CONFIG = ConfigurationFactory.getInstance();

    private static final boolean ONLY_CARE_UPDATE_COLUMNS = CONFIG.getBoolean(
            ConfigurationKeys.TRANSACTION_UNDO_ONLY_CARE_UPDATE_COLUMNS, DefaultValues.DEFAULT_ONLY_CARE_UPDATE_COLUMNS);

    /**
     * Instantiates a new Update executor.
     *
     * @param statementProxy    the statement proxy
     * @param statementCallback the statement callback
     * @param sqlRecognizer     the sql recognizer
     */
    public UpdateExecutor(StatementProxy<S> statementProxy, StatementCallback<T, S> statementCallback,
                          SQLRecognizer sqlRecognizer) {
        super(statementProxy, statementCallback, sqlRecognizer);
    }

    @Override
    protected TableRecords beforeImage() throws SQLException {
        ArrayList<List<Object>> paramAppenderList = new ArrayList<>();
        TableMeta tmeta = getTableMeta();
        // 构建出 update 操作 所影响到的 rows 并且要 在 返回的 filed 中 加上主键
        String selectSQL = buildBeforeImageSQL(tmeta, paramAppenderList);
        return buildTableRecords(tmeta, selectSQL, paramAppenderList);
    }

    private String buildBeforeImageSQL(TableMeta tableMeta, ArrayList<List<Object>> paramAppenderList) {
        // ast 信息
        SQLUpdateRecognizer recognizer = (SQLUpdateRecognizer) sqlRecognizer;
        // 获取更新的 field  update table set filed1 = ? , filed2 = ? where id = ? 里面的 filed1 和 filed2 如果是带 别名的 就是 别名.filed 不是原表名
        List<String> updateColumns = recognizer.getUpdateColumns();

        // 如果更新的包含主键信息 那么直接抛出异常
        assertContainsPKColumnName(updateColumns);


        StringBuilder prefix = new StringBuilder("SELECT ");
        StringBuilder suffix = new StringBuilder(" FROM ").append(getFromTableInSQL());
        // 获取 where aaa = 1 and ssss = 'aa' 获取where 后面的条件  并且 用 \n 分隔开
        // 并且 获取 对应索引 下的列值 但是 为什么 paramAppenderList 是一个list没搞明白
        // 获取 update table set aaa='1' where userid = ?
        //  拿到where 条件 并且 把 paramMap 中的 参数 放到 paramAppenderList index 0 的list 中
        String whereCondition = buildWhereCondition(recognizer, paramAppenderList);
        if (StringUtils.isNotBlank(whereCondition)) {
            suffix.append(WHERE).append(whereCondition);
        }
        String orderBy = recognizer.getOrderBy();
        if (StringUtils.isNotBlank(orderBy)) {
            suffix.append(orderBy);
        }
        ParametersHolder parametersHolder = statementProxy instanceof ParametersHolder ? (ParametersHolder)statementProxy : null;
        String limit = recognizer.getLimit(parametersHolder, paramAppenderList);
        if (StringUtils.isNotBlank(limit)) {
            suffix.append(limit);
        }
        suffix.append(" FOR UPDATE");
        StringJoiner selectSQLJoin = new StringJoiner(", ", prefix.toString(), suffix.toString());
        /**
         *  如果update set a.filed , a.filed1 where condition = ?
         *  如果 ONLY_CARE_UPDATE_COLUMNS true 代表 在 预查询出 操作sql 之前的镜像 的 sql 只 查询 update 中的 列。
         *  但是如果 update 中的列不包含 主键 通过 update 中原始的条件 并且到 select 中 并且返回这些 数据的主键信息
         */
        if (ONLY_CARE_UPDATE_COLUMNS) {
            if (!containsPK(updateColumns)) {
                selectSQLJoin.add(getColumnNamesInSQL(tableMeta.getEscapePkNameList(getDbType())));
            }
            for (String columnName : updateColumns) {
                selectSQLJoin.add(columnName);
            }
        } else {
            for (String columnName : tableMeta.getAllColumns().keySet()) {
                selectSQLJoin.add(ColumnUtils.addEscape(columnName, getDbType()));
            }
        }
        return selectSQLJoin.toString();
    }

    @Override
    protected TableRecords afterImage(TableRecords beforeImage) throws SQLException {
        TableMeta tmeta = getTableMeta();
        if (beforeImage == null || beforeImage.size() == 0) {
            return TableRecords.empty(getTableMeta());
        }
        String selectSQL = buildAfterImageSQL(tmeta, beforeImage);
        ResultSet rs = null;
        try (PreparedStatement pst = statementProxy.getConnection().prepareStatement(selectSQL)) {
            // 把问号变为 主键实参
            SqlGenerateUtils.setParamForPk(beforeImage.pkRows(), getTableMeta().getPrimaryKeyOnlyName(), pst);
            rs = pst.executeQuery();
            return TableRecords.buildRecords(tmeta, rs);
        } finally {
            IOUtil.close(rs);
        }
    }

    // 查询出更新之后的数据 的sql 还未注入 主键
    private String buildAfterImageSQL(TableMeta tableMeta, TableRecords beforeImage) throws SQLException {
        StringBuilder prefix = new StringBuilder("SELECT ");
        String whereSql = SqlGenerateUtils.buildWhereConditionByPKs(tableMeta.getPrimaryKeyOnlyName(), beforeImage.pkRows().size(), getDbType());
        String suffix = " FROM " + getFromTableInSQL() + " WHERE " + whereSql;
        StringJoiner selectSQLJoiner = new StringJoiner(", ", prefix.toString(), suffix);
        if (ONLY_CARE_UPDATE_COLUMNS) {
            SQLUpdateRecognizer recognizer = (SQLUpdateRecognizer) sqlRecognizer;
            List<String> updateColumns = recognizer.getUpdateColumns();
            if (!containsPK(updateColumns)) {
                selectSQLJoiner.add(getColumnNamesInSQL(tableMeta.getEscapePkNameList(getDbType())));
            }
            for (String columnName : updateColumns) {
                selectSQLJoiner.add(columnName);
            }
        } else {
            for (String columnName : tableMeta.getAllColumns().keySet()) {
                selectSQLJoiner.add(ColumnUtils.addEscape(columnName, getDbType()));
            }
        }
        return selectSQLJoiner.toString();
    }

}
