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
package io.seata.config;

import java.util.Objects;

import io.seata.common.exception.NotSupportYetException;
import io.seata.common.loader.EnhancedServiceLoader;
import io.seata.common.loader.EnhancedServiceNotFoundException;
import io.seata.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type Configuration factory.
 *
 * @author slievrly
 * @author Geng Zhang
 */
public final class ConfigurationFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationFactory.class);

    private static final String REGISTRY_CONF_DEFAULT = "registry";
    private static final String ENV_SYSTEM_KEY = "SEATA_ENV";
    public static final String ENV_PROPERTY_KEY = "seataEnv";

    private static final String SYSTEM_PROPERTY_SEATA_CONFIG_NAME = "seata.config.name";

    private static final String ENV_SEATA_CONFIG_NAME = "SEATA_CONFIG_NAME";


    // 这个 配置 的 register.con 实体文件
    // 不管你是不是spring 为容器基础的 项目 他都会形成一个代理 首先去 spring 容器中获取
    // 没有在去 register.conf去获取。 因为以spring 为起初的话  配置key 都是 seata.xxx.xxx 就可以通过这个来识别 是不是去spring容器获取了.
    // 如果是 seata.xxx 开头的 但是不是spring容器 启动的话 objectHolder 找不到 applicationContext 直接就报错了。
    public static Configuration CURRENT_FILE_INSTANCE;

    // 上面这个加载的是 register.con  厦门那个加载的是 配置中心的 conf 不是同一个逻辑层次

    static {
        load();
    }

    private static void load() {
        /**
         *  首先看 有没有 启动时 指定 没有就默认的 registry.conf
         */
        String seataConfigName = System.getProperty(SYSTEM_PROPERTY_SEATA_CONFIG_NAME);
        if (seataConfigName == null) {
            seataConfigName = System.getenv(ENV_SEATA_CONFIG_NAME);
        }
        if (seataConfigName == null) {
            seataConfigName = REGISTRY_CONF_DEFAULT;
        }
        String envValue = System.getProperty(ENV_PROPERTY_KEY);
        if (envValue == null) {
            envValue = System.getenv(ENV_SYSTEM_KEY);
        }


        Configuration configuration =
                (envValue == null)
                        ?
                new FileConfiguration(seataConfigName, false)
                        :
                        new FileConfiguration(seataConfigName + "-" + envValue, false);
        Configuration extConfiguration = null;
        try {
            // 加载扩展的容器  spring  获取eky 对应的value 的时候 重spring 容器中获取 形成一个代理
            extConfiguration = EnhancedServiceLoader.load(ExtConfigurationProvider.class).provide(configuration);
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("load Configuration:{}", extConfiguration == null ? configuration.getClass().getSimpleName()
                        : extConfiguration.getClass().getSimpleName());
            }
        } catch (EnhancedServiceNotFoundException ignore) {

        } catch (Exception e) {
            LOGGER.error("failed to load extConfiguration:{}", e.getMessage(), e);
        }
        // 当导入了 seata springboot start 后 以 start 的classpath下的 包为准
        CURRENT_FILE_INSTANCE = extConfiguration == null ? configuration : extConfiguration;
    }

    private static final String NAME_KEY = "name";
    private static final String FILE_TYPE = "file";

    // 配置 k-v 很很多实现
    private static volatile Configuration instance = null;

    /**
     * Gets instance.
     *
     *
     *   根据不同的 配置文件 模式 获取 配置的注册中心 【classpath:registry.conf】
     *   如果不是springboot  是普通的工程
     *   默认是需要 register.conf  文件 指定 conf 的方式  作为client端 无需 注册中的配置
     *   但是必须得有 conf 配置的模式
     *
     * @return the instance
     */
    public static Configuration getInstance() {
        if (instance == null) {
            synchronized (Configuration.class) {
                if (instance == null) {
                    instance = buildConfiguration();
                }
            }
        }
        return instance;
    }

    /**
     *  首先找到本地的 registry.conf 找到对应的 conf.type 的值  然后通过值 => spi机制 找到对应的实现类 => 初始化配置 中心实现类
     */
    private static Configuration buildConfiguration() {
        ConfigType configType;
        String configTypeName;
        try {
            // 这时候获取配置其实就是在 registry.conf 中读取
            configTypeName = CURRENT_FILE_INSTANCE.getConfig(
                    ConfigurationKeys.FILE_ROOT_CONFIG + ConfigurationKeys.FILE_CONFIG_SPLIT_CHAR
                            + ConfigurationKeys.FILE_ROOT_TYPE);

            if (StringUtils.isBlank(configTypeName)) {
                throw new NotSupportYetException("config type can not be null");
            }
            configType = ConfigType.getType(configTypeName);
        } catch (Exception e) {
            throw e;
        }
        Configuration extConfiguration = null;
        Configuration configuration;
        if (ConfigType.File == configType) {
            String pathDataId = String.join(ConfigurationKeys.FILE_CONFIG_SPLIT_CHAR,
                    ConfigurationKeys.FILE_ROOT_CONFIG, FILE_TYPE, NAME_KEY);
            String name = CURRENT_FILE_INSTANCE.getConfig(pathDataId);
            configuration = new FileConfiguration(name);
            try {
                extConfiguration = EnhancedServiceLoader.load(ExtConfigurationProvider.class).provide(configuration);
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("load Configuration:{}", extConfiguration == null
                            ? configuration.getClass().getSimpleName() : extConfiguration.getClass().getSimpleName());
                }
            } catch (EnhancedServiceNotFoundException ignore) {

            } catch (Exception e) {
                LOGGER.error("failed to load extConfiguration:{}", e.getMessage(), e);
            }
        } else {
            // 从新通过SPI 机制 获取 对应type 比如nacos 就获取 nacos 的 配置中心实现。
            configuration = EnhancedServiceLoader
                    .load(ConfigurationProvider.class, Objects.requireNonNull(configType).name()).provide();
        }
        try {
            Configuration configurationCache;
            // 这个extConfiguration 作用是 在配置了 模式是file模式的时候才会生效

            /**
             *  这个地方的 proxy 主要用于 配置中心 获取 是 需要 基于长连接或者 http 协议通信的 这里做了一个缓存
             */
            if (null != extConfiguration) {
                configurationCache = ConfigurationCache.getInstance().proxy(extConfiguration);
            } else {
                configurationCache = ConfigurationCache.getInstance().proxy(configuration);
            }
            if (null != configurationCache) {
                extConfiguration = configurationCache;
            }
        } catch (EnhancedServiceNotFoundException ignore) {

        } catch (Exception e) {
            LOGGER.error("failed to load configurationCacheProvider:{}", e.getMessage(), e);
        }
        return null == extConfiguration ? configuration : extConfiguration;
    }

    protected static void reload() {
        ConfigurationCache.getInstance().clear();
        load();
        instance = null;
        getInstance();
    }
}
