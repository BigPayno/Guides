package com.payno.guides.springs.spring4;

import org.junit.Test;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.ResolvableType;

/**
 * 泛型类型的决议
 *
 *      GenericTypeResolver->GenericTypeResolver & GenericCollectionTypeResolver(Spring 4)
 *          ->GenericTypeResolver(Spring 5)
 * @author zhaolei22
 * @date 2020/07/21
 */
public class GenericTypeResolution {

    interface A<T>{}

    class A1 implements A<String>{}

    public static void main(String[] args) {
        Class<?> aClass=GenericTypeResolver.resolveTypeArgument(A1.class,A.class);
        System.out.println(aClass);

        ResolvableType type = ResolvableType.forClass(A1.class)
                .getInterfaces()[0]
                .getGeneric(0);
        System.out.println(type.getRawClass());
    }
}
