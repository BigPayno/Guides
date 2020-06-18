package com.payno.guides.servers.mqs.amqp;

/**
 * @author payno
 * @date 2020/6/18 19:55
 * @description
 */
public class RabbitMqPorts {
    /**
     * -   4369 (epmd), 25672 (Erlang distribution)
     * -   5672, 5671 (AMQP 0-9-1 without and with TLS)
     * -   15672 (if management plugin is enabled)
     * -   61613, 61614 (if STOMP is enabled)
     * -   1883, 8883 (if MQTT is enabled)
     *
     * 4369 -- erlang发现口
     *
     * 5672 --client端通信口
     *
     * 15672 -- 管理界面ui端口
     *
     * 25672 -- server间内部通信口
     */
}
