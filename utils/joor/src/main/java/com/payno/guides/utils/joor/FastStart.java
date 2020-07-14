package com.payno.guides.utils.joor;

import org.joor.Reflect;
import org.junit.Test;

public class FastStart {

    @Test
    public void contructorAccess(){
        String hello = Reflect.on(String.class).create("helloworld").get();
        System.out.println(hello);
    }

    @Test
    public void fieldAccess(){
        Long serialVersionUID=Reflect.on(String.class).get("serialVersionUID");
        System.out.println(serialVersionUID);

        String string = "helloworld";
        //  string 类型会认为走反射
        Integer hash = Reflect.on((Object) string).get("hash");
        System.out.println(hash);
        System.out.println(Reflect.on((Object) string).field("hash").type());
    }

    @Test
    public void methodAccess(){
        String string = "Hello";
        byte[] bytes =Reflect.on((Object)string).call("getBytes").get();
        System.out.println(new String(bytes));
    }

    static interface NameAccess{
        String getName();
    }

    static class Person{
        String getName(){
            return "person";
        }
    }

    @Test
    public void proxy(){
        NameAccess access= Reflect.on(Person.class).create()
                .as(NameAccess.class);
        System.out.println(access.getName());
    }
}
