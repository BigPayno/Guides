package jdkguide.generic;

public class GenericShow {

    interface A<T extends A<T>>{
        /*
        *   自限定泛型
        * */
        T get();
    }

    class A1 implements A<A1>{
        @Override
        public A1 get() {
            return this;
        }
    }

    interface B<T extends B>{
        /*
        *   上界限定
        * */
        T get();
    }

    static class B1 implements B<B3>{
        @Override
        public B3 get() {
            return new B3();
        }
    }

    static class B2 implements B<B2>{
        @Override
        public B2 get() {
            return this;
        }
    }

    static class B3 implements B<B2>{
        @Override
        public B2 get() {
            return new B2();
        }
    }

    public static void main(String[] args) {
        new B1().get().get().get().get().get().get().get().get().get().get();
    }
}
