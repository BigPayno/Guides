package jvm.stackforover;

import jdkguide.thread.Threads;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author payno
 * @date 2020/6/9 17:29
 * @description
 */
public class ThreadStackForOver {
    public static void consume(){
        if(Thread.currentThread().getName().equals("main")){
            consume();
        }else{
            Threads.sleep(5000);
            System.out.println(Thread.currentThread());
        }
    }

    public static void main(String[] args) {
        ExecutorService executor=Executors.newFixedThreadPool(5);
        for (int i = 0; i <10000 ; i++) {
            executor.submit(ThreadStackForOver::consume);
        }
    }
}
