package advisor.feign;

import java.lang.annotation.*;

/**
 * @author payno
 * @date 2020/6/15 09:46
 * @description
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Feign {
}
