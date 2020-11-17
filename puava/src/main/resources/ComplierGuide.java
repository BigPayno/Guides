package com.payno.puava.classloader.test;

import com.payno.puava.classloader.ClassLifecycleGuide.TestPrint;

public class ComplierGuide implements TestPrint {
    @Override
    public void print() {
        System.out.println("Hello,Java Complier API!");
    }
}
