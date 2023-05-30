package com.example.springbootlab.common.mq;

import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;


@Component
public class RabbitMQReceiver {

    // 直连模式的多个消费者，会分到其中一个消费者进行消费。类似task模式
    // 通过注入 RabbitContainerFactory 对象，来设置一些属性，相当于 task 里的 channel.basicQos
    @RabbitListener(queues = MQConfig.QUEUE_SAMPLE)
    public void sampleReceive(String message) {

        System.out.println("Sample queue received message : " + message);
    }

    // 工作队列模式
    @RabbitListener(queues = MQConfig.QUEUE_WORK)
    public void workQueueReceive1(String message) {

        System.out.println("workQueueReceive 1 received message : " + message);
    }

    @RabbitListener(queues = MQConfig.QUEUE_WORK)
    public void workQueueReceive2(String message) {

        System.out.println("workQueueReceive 2 received message : " + message);
    }


    // pub/sub模式进行消息监听
    @RabbitListener(queues = MQConfig.QUEUE_FANOUT_Q1)
    public void fanoutReceive1(String message) {

        System.out.println("fanoutReceive 1 received message : " + message);
    }

    @RabbitListener(queues = MQConfig.QUEUE_FANOUT_Q2)
    public void fanoutReceive2(String message) {

        System.out.println("fanoutReceive 2 received message : " + message);
    }


    // Routing 路由模式
    @RabbitListener(queues = MQConfig.QUEUE_DIRECT_Q1)
    public void routeReceive1(String message) {

        System.out.println("routeReceive 1 received message : " + message);
    }

    @RabbitListener(queues = MQConfig.QUEUE_DIRECT_Q2)
    public void routeReceive2(String message) {

        System.out.println("routeReceive 2 received message : " + message);
    }


    // topic 模式
    // 注意这个模式会有优先匹配原则。例如发送routingKey=hunan.IT,那匹配到hunan.*(hunan.IT,hunan.eco),之后就不会再去匹配*.ITd
    @RabbitListener(queues = MQConfig.QUEUE_TOPIC_Q1)
    public void topicReceive1(String message) {
        System.out.println("topicReceive 1 received message : " + message);
    }

    @RabbitListener(queues = MQConfig.QUEUE_TOPIC_Q2)
    public void topicReceive2(String message) {
        System.out.println("topicReceive 2 received  message : " + message);
    }
}
