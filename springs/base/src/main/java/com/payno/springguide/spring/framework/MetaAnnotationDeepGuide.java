package com.payno.springguide.spring.framework;

import org.junit.Test;
import org.springframework.core.annotation.AnnotationFilter;
import org.springframework.core.annotation.RepeatableContainers;

import java.lang.annotation.Repeatable;

public class MetaAnnotationDeepGuide {

    static final String PAYNO = "com.payno";

    public @interface Payno{ }

    @Test
    public void annotationFilters(){
        AnnotationFilter annotationFilter1 = AnnotationFilter.packages(PAYNO);
        System.out.println(annotationFilter1.matches(Payno.class));
        AnnotationFilter annotationFilter2 = AnnotationFilter.JAVA;
        System.out.println(annotationFilter2.matches(Repeatable.class));
    }

    @Test
    public void repeatableContainers(){
        RepeatableContainers repeatableContainers = RepeatableContainers.standardRepeatables();
    }
}
