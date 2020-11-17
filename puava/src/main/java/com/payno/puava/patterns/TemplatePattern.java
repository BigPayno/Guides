package com.payno.puava.patterns;

import java.util.concurrent.locks.LockSupport;

public class TemplatePattern {

    static abstract class AbstractRunner implements Runnable{
        volatile Thread next;

        public AbstractRunner(Thread next) {
            this.next = next;
        }

        abstract boolean shouldPark();
        abstract void doPark();
        abstract void doUnparkNext();
        abstract void runInternal();

        @Override
        public void run() {
            if(shouldPark()){
                doPark();
            }
            runInternal();
            doUnparkNext();
        }
    }

    static class Runner extends AbstractRunner{

        boolean isFirst;

        @Override
        boolean shouldPark() {
            return !isFirst;
        }

        @Override
        void doPark() {
            LockSupport.park(Thread.currentThread());
        }

        @Override
        void doUnparkNext() {
            if(next!=null){
                LockSupport.unpark(next);
            }
        }

        @Override
        void runInternal() {
            System.out.println(Thread.currentThread().getName());
        }

        public Runner(Thread next, boolean isFirst) {
            super(next);
            this.isFirst = isFirst;
        }
    }

    public static void main(String[] args) {
        Thread d = new Thread(new Runner(null,false), "4");
        Thread c = new Thread(new Runner(d,false), "3");
        Thread b = new Thread(new Runner(c,false), "2");
        Thread a = new Thread(new Runner(b,true), "1");
        a.start();
        b.start();
        c.start();
        d.start();
    }

}
