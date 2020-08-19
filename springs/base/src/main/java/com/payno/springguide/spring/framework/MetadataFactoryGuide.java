package com.payno.springguide.spring.framework;

import org.junit.Test;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Arrays;

/**
 * 元数据
 *      MetadataReaderFactory
 *      MetadataReader
 *      ClassMetadata
 *      AnnotationMetadata
 *      MergedAnnotations
 * @author zhaolei22
 * @date 2020/08/19
 */
public class MetadataFactoryGuide {

    @Component("hello")
    static class Hello implements Comparable<Hello>{
        @Override
        public int compareTo(Hello o) {
            return 0;
        }
    }

    @Repository("payno")
    static class Repo{}

    @Test
    public void metadata() throws IOException {
        MetadataReaderFactory metadataReaderFactory = new SimpleMetadataReaderFactory();
        MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(Hello.class.getName());
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        Arrays.stream(classMetadata.getInterfaceNames()).forEach(System.out::println);
        MergedAnnotations mergedAnnotations = annotationMetadata.getAnnotations();
        Component component = mergedAnnotations.get(Component.class).synthesize();
        System.out.println(component.value());
    }

    @Test
    public void metadata2()  throws IOException {
        MetadataReaderFactory metadataReaderFactory = new SimpleMetadataReaderFactory();
        MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(Repo.class.getName());
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        MergedAnnotations mergedAnnotations = annotationMetadata.getAnnotations();
        Component component = mergedAnnotations.get(Component.class).synthesize();
        System.out.println(component.value());
    }
}
