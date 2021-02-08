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

/**
 * the interface ext configuration provider
 * @author xingfudeshi@gmail.com
 *
 *  提供的扩展接口 因为如果是springboot的话 配置没有单独 放到 一个文件中 而是放到了 spring boot的yml配置文件中去了
 */
public interface ExtConfigurationProvider {
    /**
     * provide a AbstractConfiguration implementation instance
     * @param originalConfiguration
     * @return configuration
     */
    Configuration provide(Configuration originalConfiguration);
}
