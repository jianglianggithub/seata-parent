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
package io.seata.spring.boot.autoconfigure.provider;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import io.seata.common.holder.ObjectHolder;
import io.seata.config.Configuration;
import io.seata.config.ExtConfigurationProvider;
import org.apache.commons.lang.StringUtils;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.ApplicationContext;


import static io.seata.common.util.StringFormatUtils.DOT;
import static io.seata.spring.boot.autoconfigure.StarterConstants.PROPERTY_MAP;
import static io.seata.spring.boot.autoconfigure.StarterConstants.SEATA_PREFIX;
import static io.seata.spring.boot.autoconfigure.StarterConstants.SERVICE_PREFIX;
import static io.seata.spring.boot.autoconfigure.StarterConstants.SPECIAL_KEY_GROUPLIST;
import static io.seata.spring.boot.autoconfigure.StarterConstants.SPECIAL_KEY_VGROUP_MAPPING;

/**
 * @author xingfudeshi@gmail.com
 */
public class SpringBootConfigurationProvider implements ExtConfigurationProvider {
    private static final String INTERCEPT_METHOD_PREFIX = "get";

    @Override
    public Configuration provide(Configuration originalConfiguration) {
        return (Configuration) Enhancer.create(originalConfiguration.getClass(), new MethodInterceptor() {
            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy)
                throws Throwable {
                if (method.getName().startsWith(INTERCEPT_METHOD_PREFIX) && args.length > 0) {
                    Object result = null;
                    String rawDataId = (String) args[0];
                    if (args.length == 1) {
                        result = get(convertDataId(rawDataId));
                    } else if (args.length == 2) {
                        result = get(convertDataId(rawDataId), args[1]);
                    } else if (args.length == 3) {
                        result = get(convertDataId(rawDataId), args[1], (Long) args[2]);
                    }
                    if (result != null) {
                        //If the return type is String,need to convert the object to string
                        if (method.getReturnType().equals(String.class)) {
                            return String.valueOf(result);
                        }
                        return result;
                    }
                }

                return method.invoke(originalConfiguration, args);
            }
        });
    }

    private Object get(String dataId, Object defaultValue, long timeoutMills) throws IllegalAccessException {
        return get(dataId, defaultValue);

    }

    private Object get(String dataId, Object defaultValue) throws IllegalAccessException {
        Object result = get(dataId);
        if (result == null) {
            return defaultValue;
        }
        return result;
    }

    private Object get(String dataId) throws IllegalAccessException {
        // a.b.c.d  拿到 d
        String propertySuffix = getPropertySuffix(dataId);
        // 拿到 a.b.c   通过前缀拿到 对应的 springboot 的 自动装配 entity
        Class propertyClass = getPropertyClass(getPropertyPrefix(dataId));
        if (propertyClass != null) {
            // 这个地方 如果找不到 对应的 objHolder 直接就报错了。。
            // 这也是为什么 eeata spring start 是 最后实例化scanner 就是为了防止启动的时候 在 applicationcontext 还没有放入这个里面 就
            // 开始init 就直接报错了
            Object propertyObject = ObjectHolder.INSTANCE.getObject(ApplicationContext.class).getBean(propertyClass);
            Optional<Field> fieldOptional = Stream.of(propertyObject.getClass().getDeclaredFields()).filter(
                f -> f.getName().equalsIgnoreCase(propertySuffix)).findAny();
            if (fieldOptional.isPresent()) {
                Field field = fieldOptional.get();
                field.setAccessible(true);
                Object valueObject = field.get(propertyObject);
                if (valueObject instanceof Map) {
                    String key = StringUtils.substringAfterLast(dataId, String.valueOf(DOT));
                    valueObject = ((Map) valueObject).get(key);
                }
                return valueObject;
            }
        }
        return null;
    }

    /**
     * convert data id
     *
     * @param rawDataId
     * @return dataId
     */
    private String convertDataId(String rawDataId) {
        if (rawDataId.endsWith(SPECIAL_KEY_GROUPLIST)) {
            String suffix = StringUtils.removeEnd(rawDataId, DOT + SPECIAL_KEY_GROUPLIST);
            //change the format of default.grouplist to grouplist.default
            return SERVICE_PREFIX + DOT + SPECIAL_KEY_GROUPLIST + DOT + suffix;
        }
        return SEATA_PREFIX + DOT + rawDataId;
    }

    /**
     * Get property prefix
     *
     * @param dataId
     * @return propertyPrefix
     */
    private String getPropertyPrefix(String dataId) {
        if (dataId.contains(SPECIAL_KEY_VGROUP_MAPPING)) {
            return SERVICE_PREFIX;
        }
        if (dataId.contains(SPECIAL_KEY_GROUPLIST)) {
            return SERVICE_PREFIX;
        }
        return StringUtils.substringBeforeLast(dataId, String.valueOf(DOT));
    }

    /**
     * Get property suffix
     *
     * @param dataId
     * @return propertySuffix
     */
    private String getPropertySuffix(String dataId) {
        if (dataId.contains(SPECIAL_KEY_VGROUP_MAPPING)) {
            return SPECIAL_KEY_VGROUP_MAPPING;
        }
        if (dataId.contains(SPECIAL_KEY_GROUPLIST)) {
            return SPECIAL_KEY_GROUPLIST;
        }
        return StringUtils.substringAfterLast(dataId, String.valueOf(DOT));
    }

    public static void main(String[] args) {

        String s1 = StringUtils.substringBeforeLast("a.b.c.d", String.valueOf(DOT));

        System.out.println(s1);
    }

    /**
     * Get property class
     *
     *  根据 前缀找到 spring boot start 的配置的默认 配置
     *
     * @param propertyPrefix
     * @return propertyClass
     */
    private Class getPropertyClass(String propertyPrefix) {
        return PROPERTY_MAP.entrySet().stream()
            .filter(e -> propertyPrefix.equals(e.getKey()))
            .findAny()
            .map(Map.Entry::getValue)
            .orElse(null);
    }
}
