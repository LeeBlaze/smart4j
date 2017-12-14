package org.smart4j.proxy;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Create by Lee on 2017/12/13
 */
public class ProxyManager {

    public static <T>T createProxy(final Class<?> targetClass){
       return (T)Enhancer.create(targetClass, new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

                ProxyClain proxyClain = new ProxyClain(targetClass,method, methodProxy, o, objects);
                return proxyClain.doProxyChain();
            }
        });
    }
}
