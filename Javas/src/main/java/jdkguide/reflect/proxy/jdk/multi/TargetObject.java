package jdkguide.reflect.proxy.jdk.multi;

import org.springframework.context.annotation.Primary;

/**
 * @author payno
 * @date 2020/6/15 09:19
 * @description
 */
public class TargetObject implements Hello, Print {
    @Override
    public void hello() {
        System.err.println("hello");
    }

    @Override
    public void print() {
        System.err.println("print");
    }
}
