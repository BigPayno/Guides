package advisor.imports;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

/**
 * @author payno
 * @date 2020/6/3 11:30
 * @description
 */
@Component
public class ImportAwareRunner implements ImportAware, ApplicationRunner {

    AnnotationMetadata annotationMetadata;
    /**
     *  该接口会将所有的@Import元注解为基础的注解信息获得
     */
    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        annotationMetadata = importMetadata;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.err.println(annotationMetadata.getAnnotationTypes());
    }
}
