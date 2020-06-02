package com.payno.transaction.faststart;

/**
 * @author payno
 * @date 2020/6/2 10:47
 * @description
 */
public class MementoDemo {
    public static void main(String[] args) {
        IMementoManager defaultManager =new IMementoManager.Default();
        IMementoTemplate defaultTemplate = new IMementoTemplate.Default(defaultManager);
        defaultTemplate.store(defaultManager.createMemento("hello"));
        defaultTemplate.store(defaultManager.createMemento("payno"));
        System.out.println(defaultTemplate.get());
        defaultTemplate.restoreMemento();
        System.out.println(defaultTemplate.get());
    }
}
