package com.payno.guides.servers.mqs.amqp;

/**
 * @author payno
 * @date 2020/6/18 18:39
 * @description
 */
public class AmqpMessageGuide {
    /**
     *  属性 	            类型 	            用途 	        使用建议或特殊用法
     * app-id 	            short-string 	应用程序 	        用于发布消息的应用程序
     * content-encoding 	short-string 	应用程序 	        指定消息体是否以某种特殊方式编码，如zlib、deflate或Base64
     * content-type 	    short-string 	应用程序 	        使用mime-types指定消息体的类型
     * correlation-id 	    short-string 	应用程序 	        如果消息引用了某个其他消息或具有唯一标识的项目，那么correlation-id可以用来指定这种引用关系
     * delivery-mode 	    octet 	        RabbitMQ 	        值为1告诉RabbitMQ可以将消息保存在内存中；值为2表示它也应该被写入磁盘
     * expiration 	        short-string 	RabbitMQ 	        用文本字符串表示的纪元时间或者UNIX时间戳，表示消息的过期时间
     * headers          	table 	        应用程序/RabbitMQ 	一个自由格式的键值表，可以使用它来添加消息相关的附加元数据；RabbitMQ也可以基于它进行路由
     * message-id 	        short-string 	应用程序 	        唯一的标识符，例如在应用程序中可以使用uuid来标识消息
     * priority 	        octet 	        RabbitMQ 	        队列中标识消息的优先顺序
     * timestamp 	        timestamp 	    应用程序 	        用文本字符串表示的纪元时间或者UNIX时间戳，表示消息的创建时间
     * type 	            short-string 	应用程序 	        一个文本字符串，用于表示应用程序中描述消息或有效负载的类型
     * user-id 	            short-string 	应用程序/RabbitMQ 	一个自由格式的字符串，如果启用该属性，RabbitMQ会验证当前连接的用户，若不匹配则丢弃消息
     */
}
