package com.payno.puava.patterns;

import org.junit.Test;

public class DoubleBind {

    interface Var{
        default void dynamicBind(){
            System.out.println("Var");
        }
    }

    class A implements Var{
        @Override
        public void dynamicBind() {
            System.out.println("Var A");
        }
    }

    class B implements Var{
        @Override
        public void dynamicBind() {
            System.out.println("Var B");
        }
    }

    private void staticBind(Var var){
        System.out.println("var");
    }

    private void staticBind(A a){
        System.out.println("var a");
    }

    private void staticBind(B b){
        System.out.println("var b");
    }

    @Test
    public void bind(){
        Var var = new A();
        A varA = new A();
        B varB = new B();
        staticBind(var);
        staticBind(varA);
        staticBind(varB);
        var.dynamicBind();
        varA.dynamicBind();
        varB.dynamicBind();
    }

    interface Visitor{
        void visit(Element element);
        void visit(ElementA element);
        void visit(ElementB element);
    }

    interface Element{
        default void accept(Visitor visitor){
            visitor.visit(this);
        }
    }

    class ElementA implements Element{

    }

    class ElementB implements Element{

    }
}
