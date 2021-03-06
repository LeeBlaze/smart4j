package org.smart4j.util;

/**
 * Create by Lee on 2017/12/12
 */
public final class HelperLoader {


    public static void init(){

        Class<?>[] classes = {
          ClassHelper.class,
          BeanHelper.class,
          AopHelper.class,
          IocHelper.class,
          ControllerHelper.class
        };

        for (Class<?> cls : classes){
            ClassUtil.loadClass(cls.getName(), false);
        }
    }
}
