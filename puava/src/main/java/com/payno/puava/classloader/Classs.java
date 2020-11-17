package com.payno.puava.classloader;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;

public final class Classs {

    public static boolean isPresent(String name) {
        try {
            Thread.currentThread().getContextClassLoader().loadClass(name);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean isLoaded(String name){
        Method method = null;
        try {
            method = ClassLoader.class.getDeclaredMethod("findLoadedClass",String.class);
            method.setAccessible(true);
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            MethodHandle methodHandle = lookup.unreflect(method);
            method.setAccessible(false);
            Class<?> clazz = (Class<?>) methodHandle.invoke(Thread.currentThread().getContextClassLoader(), name);
            return clazz!=null;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }
}
