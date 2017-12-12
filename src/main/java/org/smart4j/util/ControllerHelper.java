package org.smart4j.util;

import org.smart4j.annotation.Action;
import org.smart4j.bean.Handler;
import org.smart4j.bean.Request;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Create by Lee on 2017/12/12
 */
public final class ControllerHelper {

    private static final Map<Request,Handler> ACTION_MAP = new HashMap<>();

    static {

        Set<Class<?>> controllerClassSet =  ClassHelper.getControllerClassSet();

        if (!controllerClassSet.isEmpty()){
            for (Class<?> cls : controllerClassSet){

                Method[] methods = cls.getMethods();
                for (Method method:methods){
                    if (method.isAnnotationPresent(Action.class)){

                        Action action = method.getAnnotation(Action.class);
                        String mapping = action.value();

                        //简单验证URL规则：get:/hello

                        if (mapping.matches("\\w+:/\\w*")){

                            String[] requestInfo = mapping.split(":");
                            String requestMehod = requestInfo[0];
                            String requestPath = requestInfo[1];

                            Request request = new Request(requestMehod, requestPath);
                            Handler handler = new Handler(cls, method);
                            ACTION_MAP.put(request, handler);
                        }
                    }
                }

            }
        }

    }

    public static Handler getHandler(String requestMethod, String requestPath){
        Request request = new Request(requestMethod, requestPath);
        return ACTION_MAP.get(request);
    }

}
