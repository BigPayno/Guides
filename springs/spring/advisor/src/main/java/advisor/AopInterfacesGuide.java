package advisor;

/**
 * @author payno
 * @date 2020/6/1 11:42
 * @description
 */
public class AopInterfacesGuide {
    /**
     *  实现该接口的类不会被代理
     *
     *   1).  如果bean实现了Advisor接口，不能创建通知器的代理对象。
     *
     *   2).  如果bean实现了Advice接口，不能创建通知的代理对象。
     *
     *   3).  如果bean实现了AopInfrastructureBean接口，实现了该接口的类是Spring's AOP系统类，不能被代理。
     *
     *   4).  如果当前bean是切面bean，不能创建切面bean的代理对象。
     *
     *   5).  如果当前bean的RootBeanDefinition成员变量synthetic为true，不能创建合成bean的代理对象。
     */
}
