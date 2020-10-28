package com.payno.puava;

import org.springframework.util.ClassUtils;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;

public class Main {

    public static boolean isPresent(String name) {
        try {
            Thread.currentThread().getContextClassLoader().loadClass(name);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean isLoaded0(String name) throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType methodType = MethodType.methodType(Class.class, String.class);
        MethodHandle methodHandle = lookup.findVirtual(ClassLoader.class, "findLoadedClass", methodType);
        Class<?> clazz = (Class<?>) methodHandle.invoke(Thread.currentThread().getContextClassLoader(), name);
        return clazz!=null;
    }

    public static boolean isLoaded(String name) throws Throwable {
        Method[] methods = ClassLoader.class.getDeclaredMethods();
        Method method = ClassLoader.class.getDeclaredMethod("findLoadedClass",String.class);
        method.setAccessible(true);
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle methodHandle = lookup.unreflect(method);
        Class<?> clazz = (Class<?>) methodHandle.invoke(Thread.currentThread().getContextClassLoader(), name);
        return clazz!=null;
    }

    public static class Inner{

    }

    public static void main(String[] args) throws Throwable {
        System.out.println(
                isLoaded("com.payno.puava.Main$Inner")
        );
        System.out.println(
                isPresent("com.payno.puava.Main$Inner")
        );
        System.out.println(
                isLoaded("com.payno.puava.Main$Inner")
        );
        System.out.println(
                isLoaded("com.payno.puava.Documents")
        );
        System.out.println(
                ClassUtils.isPresent("com.payno.puava.Documents",Thread.currentThread().getContextClassLoader())
        );
        System.out.println(
                isLoaded("com.payno.puava.Documents")
        );
    }

}
