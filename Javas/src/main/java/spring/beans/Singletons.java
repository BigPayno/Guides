package spring.beans;

import org.springframework.util.function.SingletonSupplier;

public class Singletons {

    static class Person{

    }

    public static void main(String[] args) {
        SingletonSupplier<Person> singletonSupplier = SingletonSupplier.of(new Person());
        Person singleton = singletonSupplier.get();
    }
}
