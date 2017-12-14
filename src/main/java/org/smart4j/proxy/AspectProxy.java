package org.smart4j.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Create by Lee on 2017/12/13
 */
public abstract class AspectProxy implements Proxy {


    Logger logger = LoggerFactory.getLogger(AspectProxy.class);

    @Override
    public Object doProxy(ProxyClain clain) throws Throwable {

        Object result = null;
        Class<?> targetClass = clain.getTargetClass();
        Method method = clain.getTargetMethod();
        Object[] params = clain.getMethodParams();

        try {
            if (intercept(targetClass, method, params)){
                before(targetClass, method, params);
                result = clain.doProxyChain();
                after(targetClass, method, params, result);
            }
        }catch (Exception e){
            logger.error("invoke error",e);
        }


        return result;
    }



    public void before(Class<?>cls, Method method, Object[] params){

        System.out.println("before invoke method "+method.getName());
    }

    public void after(Class<?> cls, Method method,Object[] params, Object result){

        System.out.println("invoke method " + method.getName() + " over the result is "+result.toString());
    }



    public boolean intercept(Class<?>cls, Method method, Object[] params) throws Throwable{
        return true;
    }
}
