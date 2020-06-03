package com.payno.guides.springs.cache;

import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

/**
 * @author payno
 * @date 2020/6/2 16:48
 * @description
 */
@Component
public class CacheRunner implements ApplicationRunner, ApplicationContextAware,ImportAware{

    ApplicationContext context;
    AnnotationMetadata annotationMetadata;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        this.annotationMetadata = importMetadata;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        context.getBeansOfType(CacheManager.class).values().forEach(System.err::println);
        System.err.println(annotationMetadata);
    }
}
