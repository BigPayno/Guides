package com.payno.springguide.spring.framework;

import org.junit.Test;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.DefaultAopProxyFactory;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

/**
 * 代理工厂指导
 *
 * @author zhaolei22
 * @date 2020/08/17
 */
public class ProxyFactoryGuide {

    interface Interface{
        void print();
    }

    class Target implements Interface{
        @Override
        public void print(){
            System.out.println("target");
        }
    }

    class DefaultAdvice implements MethodBeforeAdvice {
        @Override
        public void before(Method method, Object[] objects, Object o) throws Throwable {
            System.err.println("Payno");
        }
    }

    /**
     * 快速启动
     *
     * ProxyTargetClass (是否强制使用CGLIB来实现代理)
     *      (true : 强制使用CGLIB来实现代理)
     *      (false : 不强制使用CGLIB来实现代理，首选JDK来实现代理)（默认值）
     * isOptimize (是否对生成代理策略进行优化)
     *      (true :  进行优化，（CGLIB代理）)
     *      (false : 不进行优化) （如果有接口就代理接口(使用JDK动态代理)，没有接口代理类（CGLIB代理））
     */
    @Test
    public void fastStart(){
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setProxyTargetClass(false);
        proxyFactory.setOptimize(false);
        proxyFactory.setAopProxyFactory(new DefaultAopProxyFactory());
        proxyFactory.setTarget(new Target());
        proxyFactory.setInterfaces(Interface.class);
        proxyFactory.addAdvice(new DefaultAdvice());
        Interface proxy = (Interface) proxyFactory.getProxy();
        proxy.print();
        System.out.println(proxy.getClass());
    }
}
