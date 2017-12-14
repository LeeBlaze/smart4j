package proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.smart4j.proxy.ProxyManager;

import java.lang.reflect.Method;

/**
 * Create by Lee on 2017/12/13
 */
public class CGLibTest {

    public static void main(String[] args) {
//        CGLibProxy proxy = new CGLibProxy();
//        HelloImpl hello =  proxy.getProxy(HelloImpl.class);
//        hello.sayHello();

        HelloImpl hello1 = ProxyManager.createProxy(HelloImpl.class);
        hello1.sayHello();
    }
}


class CGLibProxy implements MethodInterceptor{

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("before");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("after");
        return result;
    }

    public <T> T getProxy(Class<?>cls){
        return (T) Enhancer.create(cls, this);
    }
}