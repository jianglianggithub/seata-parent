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
package io.seata.spring.annotation.datasource;

import java.lang.reflect.Method;
import javax.sql.DataSource;

import io.seata.core.context.RootContext;
import io.seata.core.model.BranchType;
import io.seata.rm.datasource.DataSourceProxy;
import io.seata.rm.datasource.SeataDataSourceProxy;
import io.seata.rm.datasource.xa.DataSourceProxyXA;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.IntroductionInfo;
import org.springframework.beans.BeanUtils;

/**
 * @author xingfudeshi@gmail.com
 */
public class SeataAutoDataSourceProxyAdvice implements MethodInterceptor, IntroductionInfo {

    private final BranchType dataSourceProxyMode;
    private final Class<? extends SeataDataSourceProxy> dataSourceProxyClazz;

    public SeataAutoDataSourceProxyAdvice(String dataSourceProxyMode) {
        if (BranchType.AT.name().equalsIgnoreCase(dataSourceProxyMode)) {
            this.dataSourceProxyMode = BranchType.AT;
            this.dataSourceProxyClazz = DataSourceProxy.class;
        } else if (BranchType.XA.name().equalsIgnoreCase(dataSourceProxyMode)) {
            this.dataSourceProxyMode = BranchType.XA;
            this.dataSourceProxyClazz = DataSourceProxyXA.class;
        } else {
            throw new IllegalArgumentException("Unknown dataSourceProxyMode: " + dataSourceProxyMode);
        }

        //Set the default branch type in the RootContext.
        RootContext.setDefaultBranchType(this.dataSourceProxyMode);
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        // 是否拿到全局锁。暂时不知道啥情况
        if (!RootContext.requireGlobalLock()
                && dataSourceProxyMode != RootContext.getBranchType()) {
            return invocation.proceed();
        }

        /**
         *  检测反射执行的方法 在代理对象中是否有 有则执行代理对象的方法 否则直接执行 真实对象 或者下一个 拦截器的方法
         */
        Method method = invocation.getMethod();
        Object[] args = invocation.getArguments();
        Method m = BeanUtils.findDeclaredMethod(dataSourceProxyClazz, method.getName(), method.getParameterTypes());
        if (m != null) {
            SeataDataSourceProxy dataSourceProxy =
                    DataSourceProxyHolder.get().putDataSource((DataSource) invocation.getThis(), dataSourceProxyMode);
            return m.invoke(dataSourceProxy, args);
        } else {
            return invocation.proceed();
        }
    }


    /**
     *  这个主要是 让所有 代理的 datasource 实现这个接口
     * @return
     */
    @Override
    public Class<?>[] getInterfaces() {
        return new Class[]{SeataProxy.class};
    }
}
