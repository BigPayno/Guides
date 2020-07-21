package com.payno.guides.springs.spring;

import lombok.Data;
import org.springframework.beans.BeanMetadataAttribute;
import org.springframework.beans.BeanMetadataAttributeAccessor;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionCustomizer;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

public class BeanDefinitionBuilderGuide {
    @Data
    static class User{
        String name;
        String age;
    }

    @Data
    static class Context{
        @Autowired User user;
    }

    public static void main(String[] args) {
         BeanDefinition userBeanDefinition = BeanDefinitionBuilder
                .rootBeanDefinition(User.class)
                .setAutowireMode(AbstractBeanDefinition.AUTOWIRE_NO)
                .setScope(BeanDefinition.SCOPE_SINGLETON)
                .addPropertyValue("name","payno")
                .getBeanDefinition();

         BeanDefinition contextBeanDefinition = BeanDefinitionBuilder
                 .genericBeanDefinition(Context.class)
                 .setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE)
                 .getBeanDefinition();

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition("payno",userBeanDefinition);
        beanFactory.registerBeanDefinition("context",contextBeanDefinition);
        User payno = beanFactory.getBean(User.class);
        Context context = beanFactory.getBean(Context.class);
        System.out.println(payno);
        System.out.println(context);
    }
}
