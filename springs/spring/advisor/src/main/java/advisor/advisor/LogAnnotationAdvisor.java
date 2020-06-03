package advisor.advisor;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * @author payno
 * @date 2020/6/3 15:26
 * @description
 */
public class LogAnnotationAdvisor extends AbstractPointcutAdvisor implements BeanFactoryAware {

    private volatile Advice advice=new LogInterceptor();;
    /**
     *  AnnotationMatchingPointcut
     *      本处只匹配类
     *  如果要匹配方法，需要（null,Log.class,true）
     *  最后使用CompositePointcut
     */
    private volatile Pointcut pointcut = new AnnotationMatchingPointcut(Log.class,true);

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        if (this.advice instanceof BeanFactoryAware) {
            ((BeanFactoryAware)this.advice).setBeanFactory(beanFactory);
        }
    }
}
