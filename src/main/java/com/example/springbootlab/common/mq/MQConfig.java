package com.example.springbootlab.common.mq;

/**
 * 基本概念:
 * <p>
 * queue：队列，每个队列可以有多个消费者，但是一条消息只会被一个消费者消费
 * exchange:交换机，队列可以绑定交换机，交换机根据路由或者其他匹配信息将消息发送至queue
 * <p>
 * 模式介绍
 * <p>
 * simple模式：不需要交换机，直连模式。一个队列只有一个消费者
 * work模式：一个队列多个消费者
 * direct模式：需要交换机，通过交换机的路由key，精确匹配queue，并发送至对应的queue
 * topic模式：通过路由与路由key，模糊匹配的模式。可用通配符。比如key.1会被绑定路由key.*的queue获取到
 * fanout: 广播模式，不需要路由key，给所有绑定到交换机的queue
 * <p>
 * 参数介绍
 * <p>
 * durable: 设置是否持久化。durable设置true表示持久化，反之是持久化。持久化可以将将换机存盘，在服务器重启时不会丢失相关信息
 * exclusive: 创建一个只有自己可见的队列，即不允许其它用户访问，RabbitMQ允许你将一个Queue声明成为排他性的 true：排他 false：不排他
 * autoDelete: 当所有接收该queue的消费端都断开连接后， 是否自动删除队列 true：删除 false：不删除
 */
public class MQConfig {

    public static final String QUEUE_SAMPLE = "sample_queue";
    public static final String QUEUE_WORK = "work_queue";
    public static final String QUEUE_DIRECT_Q1 = "direct_q1";
    public static final String QUEUE_DIRECT_Q2 = "direct_q2";
    public static final String QUEUE_FANOUT_Q1 = "fanout_q1";
    public static final String QUEUE_FANOUT_Q2 = "fanout_q2";
    public static final String QUEUE_TOPIC_Q1 = "topic_q1";
    public static final String QUEUE_TOPIC_Q2 = "topic_q2";
    public static final String EXCHANGE_DIRECT = "ex_direct";
    public static final String EXCHANGE_FANOUT = "ex_fanout";
    public static final String EXCHANGE_TOPIC = "ex_topic";
    public static final String ROUTE_KEY_CHANGSHA = "china.changsha";
    public static final String ROUTE_KEY_BEIJING = "china.beijing";
    public static final String ROUTE_KEY_REG_1 = "changsha.*";
    public static final String ROUTE_KEY_REG_2 = "#.beijing";
    public static final String ROUTE_KEY_SHANGHAI = "china.shanghai";
    
}
