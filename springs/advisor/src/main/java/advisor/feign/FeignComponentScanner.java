package advisor.feign;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.env.Environment;
import org.springframework.core.type.filter.AnnotationTypeFilter;

/**
 * @author payno
 * @date 2020/6/15 09:53
 * @description
 *
 *
 * @ComponentScan works differently. Workflow is put shortly this:
 *
 * Find all .class files in same folder and all subfolders
 * Read .class file and wrap it into Resource object
 * Check if class has annotations that will make it good candidate
 * If class is good candidate, create bean from it.
 *
 *Classes from Spring source code to look:
 * ComponentScanAnnotationParser
 * AnnotationConfigUtils
 * ClassPathBeanDefinitionScanner
 * BeanDefinitionReaderUtils
 * DefaultListableBeanFactory
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
