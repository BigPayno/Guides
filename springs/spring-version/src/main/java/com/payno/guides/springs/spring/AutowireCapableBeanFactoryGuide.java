package com.payno.guides.springs.spring;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * 自动装配bean工厂指导能力
 *      1.AutowireCapableBeanFactory.autowireBean
 *      2.@Configurable+AspectJ
 * @author zhaolei22
 * @date 2020/07/21
 */
public class AutowireCapableBeanFactoryGuide {

    @Data
    @Accessors(chain = true)
    public static class Payno{
        String name;
        String pass;
    }

    @Data
    public static class Context{
        @Autowired
        @Qualifier("payno")
        Payno payno;
    }

    /*
    * B bean = new B();
    * AutowireCapableBeanFactory factory = applicationContext.getAutowireCapableBeanFactory();
    * factory.autowireBean( bean );
    * factory.initializeBean( bean, "bean" );
    *
    * The first method will process @Autowire fields and methods (but not classic properties).
    * The second method will invoke post processing (@PostConstruct and any defined BeanPostProcessors).
    *
    * */

    private void injectOnWebContext(){
        Context context = new Context();
        //  了解下AutowiredPostProcessor的使用
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(context);
    }

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        Payno test = new Payno().setName("payno").setPass("hello");
        beanFactory.registerSingleton("payno",test);
        System.out.println(beanFactory.getBean(Payno.class));
        Context context = new Context();
        beanFactory.autowireBean(context);
        System.out.println(context);
    }
}
