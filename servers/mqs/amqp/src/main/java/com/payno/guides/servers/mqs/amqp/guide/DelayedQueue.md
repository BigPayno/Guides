RabbitMQ的死信队列

对rabbitmq来说，产生死信的来源大致有如下几种：

    消息被拒绝（basic.reject或basic.nack）并且requeue=false.
    消息TTL过期
    队列达到最大长度（队列满了，无法再添加数据到mq中）

死信的处理方式

死信的产生既然不可避免，那么就需要从实际的业务角度和场景出发，对这些死信进行后续的处理，常见的处理方式大致有下面几种，

    丢弃，如果不是很重要，可以选择丢弃
    记录死信入库，然后做后续的业务分析或处理
    通过死信队列，由负责监听死信的应用程序进行处理
    
params.put("x-dead-letter-exchange", dleExchange);
params.put("x-dead-letter-routing-key", DLE_ROUTE_KEY);

Queue rabbitQueue = new Queue(queue, true, false, false, params);
        
Producer->Exchange->Queue->DLEExchange(DLE_ROUTE_KEY)->Queue

RabbitMq对延迟队列的支持是真的差，还不如Redis