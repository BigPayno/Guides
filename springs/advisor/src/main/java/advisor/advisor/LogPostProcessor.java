package advisor.advisor;

import org.springframework.aop.framework.autoproxy.AbstractBeanFactoryAwareAdvisingPostProcessor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Component;

/**
 * @author payno
 * @date 2020/6/3 15:22
 * @description
 */
@Component
//@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class LogPostProcessor extends AbstractBeanFactoryAwareAdvisingPostProcessor {
    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        super.setBeanFactory(beanFactory);
        this.advisor = new LogAnnotationAdvisor();
    }
}
