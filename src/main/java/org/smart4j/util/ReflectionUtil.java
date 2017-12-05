package org.smart4j.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Create by Lee on 2017/12/5
 */
public final class ReflectionUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);


    public static Object newInstance(Class<?> cls){
        if (cls == null) throw new NullPointerException();
        Object instance = null;
        try {
            instance = cls.newInstance();
        } catch (Exception e) {
            LOGGER.error("newInstance failed: "+cls.getName(),e);
            e.printStackTrace();
        }
        return instance;
    }

    public static Object invoke(Object object, Method method, Object ...args){
        Object instance = null;

        try {
          instance = method.invoke(object, args);
        } catch (Exception e) {
            LOGGER.error("invoke method:" + method.getName()+" failed",e);
            e.printStackTrace();
        }
        return instance;
    }

    public static void setField(Object object, Field field, Object instance){

        try {
            field.setAccessible(true);
            field.set(object, instance);
        } catch (Exception e) {
            LOGGER.error("set field failed",e);
            e.printStackTrace();
        }
    }

}
