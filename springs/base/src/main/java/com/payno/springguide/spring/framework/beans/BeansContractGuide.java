package com.payno.springguide.spring.framework.beans;

import lombok.Data;
import org.junit.Test;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.propertyeditors.ByteArrayPropertyEditor;
import org.springframework.beans.propertyeditors.ClassEditor;

import java.beans.PropertyEditorManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Beans 转换、配置相关
 *  Format/Converter之类不再写了
 *
 * @author zhaolei22
 * @date 2020/09/17
 */
public class BeansContractGuide {
    @Data
    public static class Bean{
        String name;
        Bean bean;
        List<String> relatedBeans;
        Map<String,String> map;
    }

    /**
     * BeanWrapper-JavaBeans规范，真好用，类似JsonPath和XmlPath
     * @see java.beans.PropertyDescriptor
     */
    @Test
    public void beanWrapper(){
        BeanWrapper beanWrapper = new BeanWrapperImpl(Bean.class);
        beanWrapper.setPropertyValue("name","payno");
        beanWrapper.setPropertyValue("bean",new Bean());
        beanWrapper.setPropertyValue("bean.name","chad");
        beanWrapper.setPropertyValue("relatedBeans",new ArrayList<>());
        beanWrapper.setPropertyValue("relatedBeans[0]","hello");
        beanWrapper.setPropertyValue("map",new HashMap<>());
        beanWrapper.setPropertyValue("map[hello]","payno");
        System.out.println(beanWrapper.getPropertyValue("name"));
        System.out.println(beanWrapper.getPropertyValue("bean.name"));
        System.out.println(beanWrapper.getPropertyValue("relatedBeans[0]"));
        System.out.println(beanWrapper.getPropertyValue("map[hello]"));
    }

    /**
     * PropertyEditor-JavaBeans规范 Object、String转换
     * @see PropertyEditorManager
     * @see ClassEditor
     *      在 Spring 中使用属性编辑的几个示例：
     *
     *     通过使用PropertyEditor实现在 bean 上设置属性。当使用java.lang.String作为在 XML 文件中声明的某些 bean 的属性值时，
     *     Spring(如果相应属性的设置器具有Class参数)将使用ClassEditor尝试将参数解析为Class对象。
     *
     *     在 Spring 的 MVC 框架中，通过使用各种PropertyEditor实现来解析 HTTP 请求参数，您可以在CommandController的所有子类
     *     中手动绑定这些实现。
     */
    @Test
    public void propertyEditor(){
        ByteArrayPropertyEditor byteArrayPropertyEditor = new ByteArrayPropertyEditor();
        byteArrayPropertyEditor.setAsText("hello");
        if(byte[].class==byteArrayPropertyEditor.getValue().getClass()){
            System.out.println(byteArrayPropertyEditor.getAsText());
        }
        ClassEditor classEditor = new ClassEditor();
        classEditor.setAsText("java.lang.String");
        System.out.println(classEditor.getValue());
    }
}
