package com.payno.springguide.spring.framework;

import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationFilter;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.annotation.MergedAnnotations.SearchStrategy;
import org.springframework.core.annotation.RepeatableContainers;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Consumer;

/**
 * 元数据
 *      MetadataReaderFactory
 *      MetadataReader
 *      ClassMetadata
 *      MethodMetadata
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

        @PostConstruct
        public void init(){ }
    }

    @Repository("payno")
    static class Repo{}

    @Test
    public void metaClass() throws IOException {
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
    public void metaAnnotation()  throws IOException {
        MetadataReaderFactory metadataReaderFactory = new SimpleMetadataReaderFactory();
        MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(Repo.class.getName());
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        MergedAnnotations mergedAnnotations = annotationMetadata.getAnnotations();
        Component component = mergedAnnotations.get(Component.class).synthesize();
        Repository repository = mergedAnnotations.get(Repository.class).synthesize();
        System.out.println(component.value());
        System.out.println(repository.value());
    }

    @Test
    public void metaMethod() throws IOException{
        MetadataReaderFactory metadataReaderFactory = new SimpleMetadataReaderFactory();
        MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(Hello.class.getName());
        Set<MethodMetadata> methodMetadataSet = metadataReader.getAnnotationMetadata().getAnnotatedMethods(PostConstruct.class.getName());
        methodMetadataSet.forEach(methodMetadata -> {
            System.out.println(methodMetadata.getMethodName());
            System.out.println(methodMetadata.getDeclaringClassName());
            System.out.println(methodMetadata.getReturnTypeName());
            PostConstruct postConstruct = methodMetadata.getAnnotations().get(PostConstruct.class).synthesize();
            System.out.println(postConstruct.annotationType());
        });
    }

    interface NoCareExceptionConsumer<T> extends Consumer<T>{
        @Override
        default void accept(T t){
            try{
                acceptThrowException(t);
            }catch (Exception var){}
        }

        void acceptThrowException(T t) throws Exception;
    }

    /**
     * 基于class文件得到类的相关信息
     *
     * @throws Exception 异常
     */
    @Test
    public void factory() throws Exception{
        ClassPathResource resource = new ClassPathResource("/BeanLifeCycleGuide.class.d");
        MetadataReaderFactory metadataReaderFactory = new SimpleMetadataReaderFactory();
        MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        Arrays.stream(classMetadata.getMemberClassNames()).forEach((NoCareExceptionConsumer<String>) s -> {
            AnnotationMetadata annotationMetadata = metadataReaderFactory.getMetadataReader(s)
                    .getAnnotationMetadata();
            if(annotationMetadata.hasAnnotation(Configuration.class.getName())){
                Set<MethodMetadata> beanMethodMetas = annotationMetadata.getAnnotatedMethods(Bean.class.getName());
                beanMethodMetas.forEach(beanMethodMeta->{
                    Bean bean = beanMethodMeta.getAnnotations().get(Bean.class).synthesize();
                    System.out.println(
                        String.format("@Bean %n" +
                                "initMethod:%s%n" +
                                "destroyMethod:%s",bean.initMethod(),bean.destroyMethod())
                    );
                    System.out.println(
                        String.format("methodName:%s%n" +
                                "returnType:%s%n",beanMethodMeta.getMethodName(),beanMethodMeta.getReturnTypeName())
                    );
                });
            }
        });
    }

    @Test
    public void annotationMetadata(){
        AnnotationMetadata annotationMetadata = AnnotationMetadata.introspect(Hello.class);
        Set<MethodMetadata> methodMetadataSet = annotationMetadata.getAnnotatedMethods(PostConstruct.class.getName());
        methodMetadataSet.forEach(methodMetadata -> {
            System.out.println(methodMetadata.getMethodName());
        });
    }

    @Test
    public void mergedAnnotation(){
        MergedAnnotations mergedAnnotations = MergedAnnotations.from(
                Repo.class, SearchStrategy.DIRECT, RepeatableContainers.standardRepeatables(), AnnotationFilter.NONE);
        Component component = mergedAnnotations.get(Component.class).synthesize();
        System.out.println(component.value());
    }
}
