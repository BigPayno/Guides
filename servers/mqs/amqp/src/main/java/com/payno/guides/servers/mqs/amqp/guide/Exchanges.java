package com.payno.guides.servers.mqs.amqp.guide;

import org.springframework.amqp.core.Exchange;

/**
 * 交换机
 *      消息到达Broker的第一站
 *      Binder.bind(Exchange).to(Queue).with(RouteKey)
 * @author zhaolei22
 * @date 2020/07/24
 */
public class Exchanges extends FastStart{

    public void fanout(){
        //群发
    }

    public void direct(){
        //只发到指定的一个或者多个
    }

    public void topic(){
        //支持模式匹配
        //Topic Exchange可以实现Direct Exchange，Fanout Exchange的效果
        //公司默认的是topicExchange
    }

    public void header(){
        //消息头匹配
    }
}
