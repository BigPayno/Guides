package com.payno.guides.springs.spring;

import org.springframework.beans.BeanMetadataAttribute;
import org.springframework.beans.BeanMetadataAttributeAccessor;
import org.springframework.beans.factory.support.RootBeanDefinition;

public class BeanMeteDataGuide {
    public static void main(String[] args) {
        BeanMetadataAttribute name = new BeanMetadataAttribute("name", "payno");
        BeanMetadataAttribute age = new BeanMetadataAttribute("age", "23");
        BeanMetadataAttributeAccessor accessor = new RootBeanDefinition();
        accessor.addMetadataAttribute(name);
        accessor.addMetadataAttribute(age);
    }
}
