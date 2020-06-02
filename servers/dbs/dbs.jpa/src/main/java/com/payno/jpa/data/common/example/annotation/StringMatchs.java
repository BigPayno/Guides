package com.payno.jpa.data.common.example.annotation;

import java.lang.annotation.*;

/**
 * @author payno
 * @date 2020/5/13 09:58
 * @description
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StringMatchs {
    StringMatch[] value();
}
