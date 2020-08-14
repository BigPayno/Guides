package com.payno.springguide.spring.framework;

import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.*;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.core.AliasRegistry;
import org.springframework.core.SimpleAliasRegistry;

import java.util.Arrays;

/**
 * bean接口能力Guide--只列举元能力（部分）
 *
 *      AbstractBeanFactory
 *          AliasRegistry
 *              别名管理，别名GetBean
 *          BeanDefinition
 *              GetBean
 *          SingletonBeanRegistry
 *              单例注册
 *          HierarchicalBeanFactory
 *              父级调用
 *          ConfigurableBeanFactory
 *              PostProcessor注册@see:PostProcessor
 *              Spring表达式@see:BeanExpressionResolver
 *              转换服务@see:ConversionService
 *      AbstractAutowireCapableBeanFactory
 *          AutowireCapableBeanFactory
 *              依赖注入，生命周期，依赖分析
 *      DefaultListableBeanFactory
 *          ListableBeanFactory
 *              列举
 *          BeanDefinitionRegistry
 *              BeanDefinition注册
 *
 *      注入等相关功能不涉及注解，而是通过BeanDefinition接口得到相关信息
 *      BeanDefinition是Bean的信息
 *      BeanFactory基于BeanDefinition进行处理（肯定是先设计BeanFactory，然后设计BeanDefinition接口
 *
 *
 * @author payno
 * @date 2020/08/14
 */
public class BeanFactoryFramework {

    /**
     * AbstractBeanFactory-aliasFeature
     *
     * AliasRegistry
     *      SimpleAliasRegistry
     *          基础的Alias能力
     *      BeanFactory implements AliasRegistry
     *          AbstractBeanFactory extends FactoryBeanRegistrySupport
     *          FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry
     *          DefaultSingletonBeanRegistry extends SimpleAliasRegistry implements SingletonBeanRegistry
     *          基础的Alias能力,通过别名获取Bean的能力
     */
    @Test
    public void aliasFeature(){
        AliasRegistry simpleAliasRegistry = new SimpleAliasRegistry();
        simpleAliasRegistry.registerAlias("payno","chad");
        String[] aliases = simpleAliasRegistry.getAliases("payno");
        Arrays.stream(aliases).forEach(System.err::println);
        //  chad 这就是SimpleAliasRegistry的能力

        AbstractBeanFactory abstractBeanFactory = new DefaultListableBeanFactory();
        AliasRegistry simpleAliasRegistry2 = abstractBeanFactory;
        SingletonBeanRegistry singletonBeanRegistry = abstractBeanFactory;

        simpleAliasRegistry2.registerAlias("payno","chad");
        String[] aliases2 = simpleAliasRegistry.getAliases("payno");
        Arrays.stream(aliases2).forEach(System.err::println);
        // chad 实现了SimpleAliasRegistry的BeanFactory继承了SimpleAliasRegistry的能力

        singletonBeanRegistry.registerSingleton("payno","hello,Payno");
        System.out.println(abstractBeanFactory.getBean("chad"));
        // hello,Payno 实现了SimpleAliasRegistry的BeanFactory可以通过别名获取Bean
    }

    /**
     * AbstractBeanFactory-singletonAndHierarchicalFeature
     *
     *      HierarchicalBeanFactory 获取父级工厂的能力,查询时会搜索父级工厂
     *      ConfigurableBeanFactory 设置父级工厂的能力
     *
     * BeanFactory 获取Bean的能力
     * AbstractBeanFactory implements SingletonBeanRegistry,HierarchicalBeanFactory,ConfigurableBeanFactory
     *      注册单例Bean的能力
     */
    @Test
    public void singletonAndHierarchicalFeature(){
        AbstractBeanFactory defaultBeanFactory = new DefaultListableBeanFactory();
        BeanFactory beanFactory = defaultBeanFactory;
        SingletonBeanRegistry singletonBeanRegistry = defaultBeanFactory;
        singletonBeanRegistry.registerSingleton("hello","payno");
        System.out.println(beanFactory.getBean("hello"));
        //  单例注册与获取

        AbstractBeanFactory defaultBeanFactory2 = new DefaultListableBeanFactory();
        HierarchicalBeanFactory hierarchicalBeanFactory = defaultBeanFactory2;
        ConfigurableBeanFactory configurableBeanFactory = defaultBeanFactory2;
        configurableBeanFactory.setParentBeanFactory(defaultBeanFactory);
        BeanFactory parentBeanFactory = hierarchicalBeanFactory.getParentBeanFactory();
        System.out.println(configurableBeanFactory.getBean("hello"));
        //  HierarchicalBeanFactory会搜索父级的Bean，因为能访问父级工厂
    }

    /**
     * AbstractAutowireCapableBeanFactory-lifeCycleAndDependencyFeature
     *  ConfigurableBeanFactory 注册PostProcessor的能力
     *  AutowireCapableBeanFactory 使用PostProcessor的能力
     *
     *  AbstractAutowireCapableBeanFactory extends AbstractBeanFactory
     *          AutowireCapableBeanFactory
     *  解析依赖
     *      AutowireCapableBeanFactory::resolveBeanByName
     *      AutowireCapableBeanFactory::resolveDependency
     *  Bean生命周期
     *      AutowireCapableBeanFactory::initializeBean
     *      AutowireCapableBeanFactory::applyBeanPostProcessorsBeforeInitialization
     *      AutowireCapableBeanFactory::applyBeanPostProcessorsAfterInitialization
     *      AutowireCapableBeanFactory::destroyBean
     *      AutowireCapableBeanFactory::populateBean
     *  autowire配置属性与对象
     *      AutowireCapableBeanFactory::autowireBeanProperties
     *      AutowireCapableBeanFactory::applyBeanPropertyValues
     *      AutowireCapableBeanFactory::applyBeanPropertyValues
     *  创建对象
     *      AutowireCapableBeanFactory::createBean
     *
     *  postProcessor
     *      ConfigurableBeanFactory 注册PostProcessor
     *      AutowireCapableBeanFactory 应用PostProcessor
     */
    @Test
    public void lifeCycleAndDependencyFeature(){
        AbstractAutowireCapableBeanFactory beanFactory = new DefaultListableBeanFactory();
        SingletonBeanRegistry singletonBeanRegistry = beanFactory;
        AbstractBeanFactory abstractBeanFactory = beanFactory;
        ConfigurableBeanFactory configurableBeanFactory = beanFactory;
        AutowireCapableBeanFactory autowireCapableBeanFactory = beanFactory;
        configurableBeanFactory.addBeanPostProcessor(new BeanPostProcessor() {
            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                return bean;
            }

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if(String.class.isAssignableFrom(bean.getClass())){
                    String obj = (String) bean;
                    return obj.concat("::Payno");
                }
                return bean;
            }
        });
        singletonBeanRegistry.registerSingleton("Hello","Hello");
        Object targetObject = abstractBeanFactory.getBean("Hello");
        Object postObject = autowireCapableBeanFactory.applyBeanPostProcessorsAfterInitialization(targetObject,"Hello");
        //  这里可以看下源码，string的不可变带来了非预期的结果
        System.out.println(targetObject);
        System.out.println(postObject);
    }

    /**
     * DefaultListableBeanFactory-beanDefinitionAndListableFeature
     *
     * DefaultListableBeanFactory
     *      extends
     *      AbstractAutowireCapableBeanFactory
     *          extends
     *          AbstractBeanFactory
     *              ConfigurableBeanFactory
     *              SingletonBeanRegistry
     *          implements
     *          AutowireCapableBeanFactory
     *      implements
     *      ConfigurableListableBeanFactory
     *          extends
     *          @New:ListableBeanFactory
     *          AutoireCapableBeanFactory
     *          ConfigurableBeanFactory
     *      @New:BeanDefinitionRegistry
     */
    @Test
    public void beanDefinitionAndListableFeature(){
        // do nothing !
    }
}
