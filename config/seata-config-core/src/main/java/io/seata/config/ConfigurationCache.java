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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import io.seata.common.util.StringUtils;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * @author funkye
 *
 *
 *  在创建 coniguration 的时候 会创建一个代理 就是当前对象实例 ，
 *
 *  因为有一次get 后就会cache 后续走cache 但是无法更新了 所以需要手动 addConfigListener 监听对应的key 更改然后修改缓存
 */
public class ConfigurationCache implements ConfigurationChangeListener {

    private static final String METHOD_PREFIX = "get";

    private static final String METHOD_LATEST_CONFIG = METHOD_PREFIX + "LatestConfig";

    // data-id  value  cache
    private static final ConcurrentHashMap<String, Object> CONFIG_CACHE = new ConcurrentHashMap<>();

    private Map<String, HashSet<ConfigurationChangeListener>> configListenersMap = new HashMap<>();


    /**
        这个func的作用就是 在添加 dataId 的同时注册一个 configCacheListener 对象
        做一个缓存罢了。
     */
    public static void addConfigListener(String dataId, ConfigurationChangeListener... listeners) {
        if (StringUtils.isBlank(dataId)) {
            return;
        }
        synchronized (ConfigurationCache.class) {
            HashSet<ConfigurationChangeListener> listenerHashSet =
                getInstance().configListenersMap.computeIfAbsent(dataId, key -> new HashSet<>());

            // 给每个DataId 添加监听器的时候 同时在注册一个 this 对象 因为当前类也是一个监听器
            // 顺便做个缓存
            if (!listenerHashSet.contains(getInstance())) {
                // 获取到全局唯一的 注册中心conf对象
                ConfigurationFactory.getInstance()
                        // 添加监听器
                        .addConfigListener(dataId, getInstance());
                listenerHashSet.add(getInstance());
            }

            // 将需要的监听器 再次addConfigListener
            if (null != listeners && listeners.length > 0) {
                for (ConfigurationChangeListener listener : listeners) {
                    if (!listenerHashSet.contains(listener)) {
                        listenerHashSet.add(listener);
                        ConfigurationFactory.getInstance().addConfigListener(dataId, listener);
                    }
                }
            }



        }
    }

    public static ConfigurationCache getInstance() {
        return ConfigurationCacheInstance.INSTANCE;
    }

    @Override
    public void onChangeEvent(ConfigurationChangeEvent event) {
        Object oldValue = CONFIG_CACHE.get(event.getDataId());
        if (null == oldValue || !oldValue.equals(event.getNewValue())) {
            if (StringUtils.isNotBlank(event.getNewValue())) {
                CONFIG_CACHE.put(event.getDataId(), event.getNewValue());
            } else {
                CONFIG_CACHE.remove(event.getDataId());
            }
        }
    }

    // 主要是做了一个本地缓存。 因为 可能 seata-conf 是基于 注册中心的 一个基于代理的性能优化。
    public Configuration proxy(Configuration originalConfiguration) {
        return (Configuration)Enhancer.create(Configuration.class,
            (MethodInterceptor)(proxy, method, args, methodProxy) -> {
                if (method.getName().startsWith(METHOD_PREFIX)
                    && !method.getName().equalsIgnoreCase(METHOD_LATEST_CONFIG)) {
                    String rawDataId = (String)args[0];
                    Object result = CONFIG_CACHE.get(rawDataId);

                    // see
                    if (null == result) {
                        result = method.invoke(originalConfiguration, args);
                        if (result != null) {
                            CONFIG_CACHE.put(rawDataId, result);
                        }
                    }


                    if (null != result && method.getReturnType().equals(String.class)) {
                        return String.valueOf(result);
                    }
                    return result;
                }
                return method.invoke(originalConfiguration, args);
            });
    }

    private static class ConfigurationCacheInstance {
        private static final ConfigurationCache INSTANCE = new ConfigurationCache();
    }

    public void clear() {
        CONFIG_CACHE.clear();
    }
}
