package spring.reflect.annotation.faststart;

import common.functions.Consumers;
import org.junit.Test;
import org.springframework.core.annotation.AliasFor;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class MetaAnnotationTest {

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Service
    public @interface RemoteService {
        @AliasFor(
                annotation = Service.class,
                attribute = "value"
        )
        String value() default "";
    }

    @RemoteService("image-svc")
    class RemoteServiceComponent{

    }

    @Test
    public void meta(){
        RemoteService remoteService = AnnotationUtils
                .getAnnotation(RemoteServiceComponent.class,RemoteService.class);
        Service service = AnnotatedElementUtils
                .findMergedAnnotation(RemoteServiceComponent.class,Service.class);
        Consumers.fluent()
                .onObjectNotNull(remoteService,System.out::println)
                .onObjectNotNull(service,System.out::println);
    }
}
