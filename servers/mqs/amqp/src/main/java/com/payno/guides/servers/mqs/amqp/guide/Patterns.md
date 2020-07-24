1.基本消息模型
    单纯的发送消息
    消息发送到broker后，exchanger只binding到唯一的queue里
    P-C:一对一
2.工作队列模型
    一个消息发送到消息队列，有多个消费者竞争消费
    消息发送到broker后，exchanger只binding到唯一的queue里
    P-C:一对多
3.订阅模型
    一个消息发送到消息队列后，给多个消费者消费
    消息发送到broker后，exchanger只binding到多个queue里
    P-C:一对多，多对多
    
Producer->Exchanger->Binder->Queue->Consumer