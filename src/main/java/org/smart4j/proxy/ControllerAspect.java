package org.smart4j.proxy;

import org.smart4j.annotation.Aspect;
import org.smart4j.annotation.Controller;
import org.smart4j.proxy.AspectProxy;

import java.lang.reflect.Method;

/**
 * Create by Lee on 2017/12/13
 */

@Aspect(Controller.class)
public class ControllerAspect extends AspectProxy {

    @Override
    public void before(Class<?> cls, Method method, Object[] params) {
        super.before(cls, method, params);
    }

    @Override
    public void after(Class<?> cls, Method method, Object[] params, Object result) {
        super.after(cls, method, params, result);
    }
}
