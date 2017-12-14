package org.smart4j.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Create by Lee on 2017/12/5
 */
public class BeanHelper {

    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<>();

    static {
        Set<Class<?>> classSet = ClassHelper.getBeanClassSet();
        classSet.forEach((cls)->{
            Object object = ReflectionUtil.newInstance(cls);
            BEAN_MAP.put(cls, object);
        });
    }

    public static Map<Class<?>, Object> getBeanMap() {
        return BEAN_MAP;
    }

    @SuppressWarnings("unckecked")
    public static <T> T getBean(Class<T> tClass){

        if (!BEAN_MAP.containsKey(tClass)){
            throw new RuntimeException("can not get bean by class: "+tClass);
        }
        return (T)BEAN_MAP.get(tClass);
    }


    public static void setBean(Class<?> cls, Object o){
        BEAN_MAP.put(cls, o);
    }
}
