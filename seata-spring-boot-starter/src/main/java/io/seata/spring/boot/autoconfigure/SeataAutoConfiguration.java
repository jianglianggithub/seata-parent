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
package io.seata.spring.boot.autoconfigure;

import io.seata.spring.annotation.GlobalTransactionScanner;
import io.seata.spring.annotation.datasource.SeataAutoDataSourceProxyCreator;
import io.seata.spring.annotation.datasource.SeataDataSourceBeanPostProcessor;
import io.seata.spring.boot.autoconfigure.properties.SeataProperties;
import io.seata.spring.boot.autoconfigure.provider.SpringApplicationContextProvider;
import io.seata.tm.api.DefaultFailureHandlerImpl;
import io.seata.tm.api.FailureHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import static io.seata.common.Constants.BEAN_NAME_FAILURE_HANDLER;
import static io.seata.common.Constants.BEAN_NAME_SPRING_APPLICATION_CONTEXT_PROVIDER;
import static io.seata.spring.annotation.datasource.AutoDataSourceProxyRegistrar.BEAN_NAME_SEATA_AUTO_DATA_SOURCE_PROXY_CREATOR;
import static io.seata.spring.annotation.datasource.AutoDataSourceProxyRegistrar.BEAN_NAME_SEATA_DATA_SOURCE_BEAN_POST_PROCESSOR;

/**
 * @author xingfudeshi@gmail.com
 */
@ComponentScan(basePackages = "io.seata.spring.boot.autoconfigure.properties")
@ConditionalOnProperty(prefix = StarterConstants.SEATA_PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
@Configuration
@EnableConfigurationProperties({SeataProperties.class})
public class SeataAutoConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(SeataAutoConfiguration.class);

    // 该bean 只是 缓存了 spring 上下文 在 seata objHolder中
    @Bean(BEAN_NAME_SPRING_APPLICATION_CONTEXT_PROVIDER)
    @ConditionalOnMissingBean(name = {BEAN_NAME_SPRING_APPLICATION_CONTEXT_PROVIDER})
    public SpringApplicationContextProvider springApplicationContextProvider() {
        return new SpringApplicationContextProvider();
    }

    // 这个作用好像是 处理 事务提交或者回滚错误的情况 的hock ？
    @Bean(BEAN_NAME_FAILURE_HANDLER)
    @ConditionalOnMissingBean(FailureHandler.class)
    public FailureHandler failureHandler() {
        return new DefaultFailureHandlerImpl();
    }

    // 创建代理对象的后置处理器 给加了seata 的事务注解的 bean对象 提供拦截
    @Bean
    @DependsOn({BEAN_NAME_SPRING_APPLICATION_CONTEXT_PROVIDER, BEAN_NAME_FAILURE_HANDLER})
    @ConditionalOnMissingBean(GlobalTransactionScanner.class)
    public GlobalTransactionScanner globalTransactionScanner(SeataProperties seataProperties, FailureHandler failureHandler) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Automatically configure Seata");
        }
        return new GlobalTransactionScanner(seataProperties.getApplicationId(), seataProperties.getTxServiceGroup(), failureHandler);
    }

    /**
     * 这个配置类主要是为了 给dataSource创建代理使用了2个 后置处理器
     */
    @Configuration
    @ConditionalOnProperty(prefix = StarterConstants.SEATA_PREFIX, name = {"enableAutoDataSourceProxy", "enable-auto-data-source-proxy"}, havingValue = "true", matchIfMissing = true)
    static class SeataDataSourceConfiguration {

        /**
         * The bean seataDataSourceBeanPostProcessor.
         *
                缓存代理对象 将所有dataSource 都返回一个 dataSourceProxy
         *
         */
        @Bean(BEAN_NAME_SEATA_DATA_SOURCE_BEAN_POST_PROCESSOR)
        @ConditionalOnMissingBean(SeataDataSourceBeanPostProcessor.class)
        public SeataDataSourceBeanPostProcessor seataDataSourceBeanPostProcessor(SeataProperties seataProperties) {
            return new SeataDataSourceBeanPostProcessor(seataProperties.getExcludesForAutoProxying(), seataProperties.getDataSourceProxyMode());
        }

        /**
         *  这个是 给dataSource创建代理 当 targetSource 的 method 在 proxySource 中有的时候先执行 代理中的 method
         *   主要用于将返回的 connection 也一样进行代理。
         */
        @Bean(BEAN_NAME_SEATA_AUTO_DATA_SOURCE_PROXY_CREATOR)
        @ConditionalOnMissingBean(SeataAutoDataSourceProxyCreator.class)
        public SeataAutoDataSourceProxyCreator seataAutoDataSourceProxyCreator(SeataProperties seataProperties) {
            return new SeataAutoDataSourceProxyCreator(seataProperties.isUseJdkProxy(),
                    seataProperties.getExcludesForAutoProxying(), seataProperties.getDataSourceProxyMode());
        }

        /**
            上面2个后置处理器只为了实现一个bean对象就是
                不明白为什么搞2个后置处理器？
             @Bean
             public DataSourceProxy dataSourceProxy(DruidDataSource druidDataSource) {
               return new DataSourceProxy(druidDataSource);
            }

         */
    }
}
