package jdkguide.reflect.proxy.jdk.multi;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ClassUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author payno
 * @date 2020/6/15 09:11
 * @description
 */
@AllArgsConstructor
public class MultiHandlerInvocation implements InvocationHandler {

    public TargetObject t;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(String.format("class[%s],interfaces%s",proxy.getClass(), ClassUtils.getAllInterfacesAsSet(proxy).toString()));
        return method.invoke(t,args);
    }

    public static void main(String[] args) {
        InvocationHandler invocationHandler=new MultiHandlerInvocation(new TargetObject());
        /**
         *  1.使用的类加载器
         *  2.实现的接口
         *  3.对应的代理拦截器
         */
        Object proxy=Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{Hello.class,Print.class},
                invocationHandler);
        if(proxy instanceof Hello){
            ((Hello) proxy).hello();
        }
        if(proxy instanceof Print){
            ((Print) proxy).print();
        }
    }
}
