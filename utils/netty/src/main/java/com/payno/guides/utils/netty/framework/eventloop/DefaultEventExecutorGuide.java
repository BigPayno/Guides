package com.payno.guides.utils.netty.framework.eventloop;

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
        /**
         *  执行器拥有一个Queue（如果是非阻塞的队列，抛出错误），会去消费
         *  判断当前运行的线程是否是执行器的线程判断是否inEventLoop
         *
         *  为了确保一个Channel的整个生命周期中的I/O事件会被一个EventLoop负责，
         *  Netty通过inEventLoop()方法来判断当前执行的线程的身份，确定它是否是
         *  分配给当前Channel以及它的EventLoop的那一个线程
         */
        /**
         * interface EventExecutorGroup extends ScheduledExecutorService, Iterable<EventExecutor>
         *     1.提供了优雅关闭事件运行组的能力
         *     2.使用EventExecutor的next来访问EventExecutor
         *  主要拓展的方法是next来调用下一个运行器
         *
         *  interface EventLoop extends OrderedEventExecutor, EventLoopGroup
         *      其中OrderedEventExecutor只是个标志接口，并未拓展
         *  主要拓展的方法是parent来获取EventLoopGroup
         *
         *  abstract class MultithreadEventExecutorGroup extends AbstractEventExecutorGroup
         *      private final EventExecutor[] children;
         *     private final Set<EventExecutor> readonlyChildren;
         *     private final AtomicInteger terminatedChildren = new AtomicInteger();
         *     private final Promise<?> terminationFuture = new DefaultPromise(GlobalEventExecutor.INSTANCE);
         *     private final EventExecutorChooserFactory.EventExecutorChooser chooser;
         * 其中AbstractEventExecutorGroup实现了ScheduledExecutorService的默认方法，其中结合了next
         *      但是本身并不实现next，而是委托给EventExecutorChooser实现，其有两个实现类，2的幂次与取余选择器
         *      推荐2的幂次可以通过位运算性能更高,线程池的状态由线程的状态实现
         * 主要拓展方法是newChild、register（绑定channel，EventLoopGroup接口的方法）
         *
         *  NioEventLoopGroup
         */
    }
}
