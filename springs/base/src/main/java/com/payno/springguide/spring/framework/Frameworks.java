package com.payno.springguide.spring.framework;

public class Frameworks {
    /*
     * @Base:
     *      资源访问：ResourceFramework
     *      配置访问：PropertyFramework
     *      转换服务：ConversionFramework -|>PropertyFramework
     *      元数据：MetaDataFramework
     *      元注解：AnnotationFramework -|>MetaDataFramework
     *      环境：EnvironmentFramework
     * @Core:
     *      BeanDefinition、BeanReader、BeanResolver：BeanDefinitionFramework
     *          -|>AnnotationFramework,MetaDataFramework...
     *      BeanScope、BeanAutowire、BeanFactory、BeanLifecycle: BeanFactoryFramework
     *          -|>BeanDefinitionFramework，ResourceFramework...
     *      Application: ApplicationFramework
     *          -|>EnvironmentFramework，BeanFactoryFramework,BeanDefinitionFramework,AnnotationFramework...
     */
}
