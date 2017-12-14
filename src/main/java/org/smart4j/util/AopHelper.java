package org.smart4j.util;

import org.smart4j.annotation.Aspect;
import org.smart4j.proxy.AspectProxy;
import org.smart4j.proxy.Proxy;
import org.smart4j.proxy.ProxyManager;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * Create by Lee on 2017/12/13
 */
public final class AopHelper {

    static {
        try {
            Map<Class<?>,Set<Class<?>>> proxyMap = createProxyMap();
            Map<Class<?>, List<Proxy>> targetMap = createTargetMap(proxyMap);
            for (Map.Entry<Class<?>, List<Proxy>> entry:targetMap.entrySet()){
                Class<?> targetClass = entry.getKey();
                List<Proxy> proxyList = entry.getValue();
                Object proxy = ProxyManager.createProxy(targetClass);
                BeanHelper.setBean(targetClass, proxy);
            }

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }





    private static Set<Class<?>> createTargetClassSet(Aspect aspect) throws Throwable{
        Set<Class<?>> targetClassSet = new HashSet<>();
        Class<? extends Annotation> annotation = aspect.value();
        if (annotation != null && !annotation.equals(Aspect.class)){
            targetClassSet.addAll(ClassHelper.getClassSetByAnnotation(annotation));
        }
        return targetClassSet;
    }

    //controllerAspect:allController
    public static Map<Class<?>,Set<Class<?>>> createProxyMap() throws Throwable{
        Map<Class<?>,Set<Class<?>>> map = new HashMap<>();

        //获取实现了拦截某bean里方法的class
        Set<Class<?>> classSet = ClassHelper.getClassSetBySuper(AspectProxy.class);

        for (Class<?> cls :classSet){
            Aspect aspect = cls.getAnnotation(Aspect.class);
            Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
            map.put(cls, targetClassSet);
        }

        return map;
    }

    private static Map<Class<?>, List<Proxy>> createTargetMap(Map<Class<?>,Set<Class<?>>> proxyMap) throws Throwable{

        Map<Class<?>, List<Proxy>> targetMap = new HashMap<>();
        for (Map.Entry<Class<?>, Set<Class<?>>> entry:proxyMap.entrySet()){
            Class<?> proxyClass = entry.getKey();
            Set<Class<?>> targetClassSet = entry.getValue();

            for (Class<?> tagetClass:targetClassSet){
                Proxy proxy = (Proxy)proxyClass.newInstance();
                if (targetMap.containsKey(tagetClass)){
                    targetMap.get(tagetClass).add(proxy);
                }else {
                    List<Proxy> proxyList = new ArrayList<>();
                    proxyList.add(proxy);
                    targetMap.put(tagetClass, proxyList);
                }
            }
        }
        return targetMap;
    }
}
