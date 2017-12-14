package org.smart4j.proxy;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Create by Lee on 2017/12/13
 */
public class ProxyClain {

    private final Class<?> targetClass;
    private final MethodProxy methodProxy;
    private final Method targetMethod;
    private final Object targetObject;
    private final Object[] methodParams;

    public ProxyClain(Class<?> targetClass,Method method, MethodProxy methodProxy, Object targetObject, Object[] methodParams) {
        this.targetClass = targetClass;
        this.methodProxy = methodProxy;
        this.targetMethod = method;
        this.targetObject = targetObject;
        this.methodParams = methodParams;
    }

    public Object doProxyChain() throws Throwable{

        Object reslut = null;

        reslut = methodProxy.invokeSuper(targetObject, methodParams);
        return reslut;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public MethodProxy getMethodProxy() {
        return methodProxy;
    }

    public Method getTargetMethod() {
        return targetMethod;
    }

    public Object[] getMethodParams() {
        return methodParams;
    }
}
