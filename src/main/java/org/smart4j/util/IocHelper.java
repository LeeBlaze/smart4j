package org.smart4j.util;

import org.smart4j.annotation.Inject;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * Create by Lee on 2017/12/5
 */
public class IocHelper {

    static {
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (!beanMap.isEmpty()){
            Set<Map.Entry<Class<?>, Object>> entrySet = beanMap.entrySet();
            entrySet.forEach((entry)->{
                Class<?> beanClass  = entry.getKey();
                Object beanInstance = entry.getValue();
                Field[] fields = beanClass.getDeclaredFields();
                for (Field field : fields){
                    if (field.isAnnotationPresent(Inject.class)){
                        Class<?> beanFieldClass = field.getType();
                        Object beanFieldInstance = beanMap.get(beanFieldClass);
                        if (beanFieldClass != null){
                            ReflectionUtil.setField(beanInstance, field, beanFieldInstance);
                        }
                    }
                }

            });
        }

    }

}
