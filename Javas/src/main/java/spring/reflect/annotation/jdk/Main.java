package spring.reflect.annotation.jdk;

import common.functions.Consumers;
import org.junit.Test;
import org.springframework.core.annotation.AnnotationUtils;

import javax.annotation.Nullable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class Main {

    enum GenderEnum{male,female;}

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Inherited
    @interface Gender{
        @Nullable GenderEnum gender();
    }

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface Gender2{
        @Nullable GenderEnum gender();
    }

    @Gender(gender = GenderEnum.male)
    @Gender2(gender = GenderEnum.male)
    interface Male{
    }

    class Men implements Male{

    }

    @Test
    public void inherited(){
        Gender gender = AnnotationUtils.getAnnotation(Men.class,Gender.class);
        Gender2 gender2 = AnnotationUtils.getAnnotation(Men.class,Gender2.class);
        Consumers.fluent()
                .onObjectNotNull(gender,System.out::println)
                .onObjectNotNull(gender2,System.out::println);
    }

    @Gender(gender = GenderEnum.female)
    @Gender2(gender = GenderEnum.female)
    class Female{

    }

    class WoMen extends Female{

    }

    /**
     * inherited2
     *      Jdk的@Inherit注解
     */
    @Test
    public void inherited2(){
        Gender gender = AnnotationUtils.getAnnotation(WoMen.class,Gender.class);
        Gender2 gender2 = AnnotationUtils.getAnnotation(WoMen.class,Gender2.class);
        Consumers.fluent()
                .onObjectNotNull(gender,System.out::println)
                .onObjectNotNull(gender2,System.out::println);
    }
}
