package com.payno.guides.servers.mqs.amqp.guide;

import org.springframework.amqp.core.Queue;

public class DeclareMessageQueue extends FastStart{

    public void messageQueue(){
        /*
        * a)消费者是无法订阅或者获取不存在的MessageQueue中信息。
        * b)消息被Exchange接受以后，如果没有匹配的Queue，则会被丢弃。
        *
        * 因此，声明消息队列应该有生产者声明
        * a) Exclusive：排他队列，如果一个队列被声明为排他队列，该队列仅对首次声明它的连接可见，并在连接断开时自动删除。
        * 这里需要注意三点：
        * 其一，排他队列是基于连接可见的，同一连接的不同信道是可以同时访问同一个连接创建的排他队列的。
        * 其二，“首次”，如果一个连接已经声明了一个排他队列，其他连接是不允许建立同名的排他队列的，这个与普通队列不同。
        * 其三，即使该队列是持久化的，一旦连接关闭或者客户端退出，该排他队列都会被自动删除的。这种队列适用于只限于一个客户端发送读取消息的应用场景。
        *
        * b)Auto-delete:自动删除，如果该队列没有任何订阅的消费者的话，该队列会被自动删除。这种队列适用于临时队列。
        * c)Durable:持久化，这个会在后面作为专门一个章节讨论。
        * d)其他选项，例如如果用户仅仅想查询某一个队列是否已存在，如果不存在，不想建立该队列，仍然可以调用queue.declare，只不过需要将参数passive设为true，
        *   传给queue.declare，如果该队列已存在，则会返回true；如果不存在，则会返回Error，但是不会创建新的队列。
        * */
        amqpAdmin.declareQueue(new Queue("Test-Queue"));
    }


    /*
    * Rabbit MQ默认是不持久队列、Exchange、Binding以及队列中的消息的，这意味着一旦消息服务器重启，所有已声明的队列，Exchange，Binding以及队列中的消息都会丢失
    * 。通过设置Exchange和MessageQueue的durable属性为true，可以使得队列和Exchange持久化，但是这还不能使得队列中的消息持久化，
    * 这需要生产者在发送消息的时候，将delivery mode设置为2，只有这3个全部设置完成后，才能保证服务器重启不会对现有的队列造成影响。
    * 这里需要注意的是，只有durable为true的Exchange和durable为ture的Queues才能绑定，否则在绑定时，RabbitMQ都会抛错的。
    * 持久化会对RabbitMQ的性能造成比较大的影响，可能会下降10倍不止。
    * */
}

