package com.payno.guides.servers.mqs.amqp.core;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Exchange;

/**
 * @author payno
 * @date 2020/6/18 19:27
 * @description
 */
public class AmpqExchangeGuide {
    /**
     * 绑定bindings的策略
     *      DirectExchange
     *      Bindings和route key一致
     *      FanoutExchange
     *      All Bindings
     *      TopicExchange
     *      Bindings满足Topic表达式
     *
     * Direct：（默认）其类型的行为是“先匹配、再投送”，即在绑定时设定一个 routing_key，消息的 routing_key 匹配时，才会被交换器投送到绑定的队列中去。
     * Topic：按规则转发消息（最灵活）。支持‘*’ （1个）和 # （0个或者多个）等通配符。
     * Headers：设置 header attribute 参数类型的交换机。
     * Fanout：转发消息到所有绑定队列。
     */
    public static void main(String[] args) {

    }
}
