package com.payno.guides.springs.spring;

import com.payno.guides.springs.spring.BeanDefinitionBuilderGuide.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.type.StandardAnnotationMetadata;

public class AnnotationBeanDefinitionGuide {
    @Data
    static class User{
        String name;
        String age;
    }

    @Data
    static class Context{
        @Autowired
        BeanDefinitionBuilderGuide.User user;
    }

    public static void main(String[] args) {
        StandardAnnotationMetadata metadata = new StandardAnnotationMetadata(Context.class);
        metadata.getAnnotations().forEach(System.err::println);
    }
}
