package advisor.feign;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.env.Environment;
import org.springframework.core.type.filter.AnnotationTypeFilter;

/**
 * @author payno
 * @date 2020/6/15 09:53
 * @description
 */
public class FeignComponentScanner extends ClassPathScanningCandidateComponentProvider {

    public FeignComponentScanner(boolean useDefaultFilters, Environment environment) {
        super(useDefaultFilters, environment);
        super.addIncludeFilter(new AnnotationTypeFilter(Feign.class));
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return true;
    }
}
