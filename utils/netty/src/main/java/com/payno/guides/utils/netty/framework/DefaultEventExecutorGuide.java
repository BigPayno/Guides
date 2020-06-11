package com.payno.guides.utils.netty.framework;

import com.payno.guides.utils.netty.utils.Threads;
import io.netty.channel.DefaultEventLoop;
import io.netty.util.concurrent.DefaultEventExecutor;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * @author payno
 * @date 2020/6/11 10:58
 * @description
 */
public class DefaultEventExecutorGuide {
    public static void main(String[] args) {
        Runnable runnable=()->{
            System.out.println("start!");
            Threads.sleep(2000);
            System.out.println("end!");
        };
        EventExecutorGroup executorGroup = new DefaultEventLoop();
        /**
         *  执行器拥有一个Queue（如果是非阻塞的队列，抛出错误），会去消费
         *  判断当前运行的线程是否是执行器的线程判断是否inEventLoop
         *
         *  为了确保一个Channel的整个生命周期中的I/O事件会被一个EventLoop负责，
         *  Netty通过inEventLoop()方法来判断当前执行的线程的身份，确定它是否是
         *  分配给当前Channel以及它的EventLoop的那一个线程
         */
        EventExecutor eventExecutor1 = new DefaultEventExecutor(executorGroup);
        EventExecutor eventExecutor2 = new DefaultEventExecutor(executorGroup);

    }
}
