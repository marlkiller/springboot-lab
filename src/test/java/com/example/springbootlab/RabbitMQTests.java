package com.example.springbootlab;

import com.example.springbootlab.common.mq.MQConfig;
import com.example.springbootlab.common.mq.RabbitMQReceiver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.nio.charset.StandardCharsets;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class RabbitMQTests extends SpringbootLabApplicationTests {


    // MockBean 来排除掉 Springboot 启动生成对 bean
    // 防止测试类,生成 MQ 服务端
    @MockBean
    private RabbitMQReceiver rabbitMQReceiver;
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    @BeforeEach
    public void before() {
        when(rabbitMQReceiver.methodWithParam("参数")).thenReturn("mock : 方法的返回值");
        System.out.println(rabbitMQReceiver.methodWithParam("参数"));

        doNothing().when(rabbitMQReceiver).methodWithoutParam();
        rabbitMQReceiver.methodWithoutParam();
    }

    // Sample queue 直连模式
    @Test
    public void sample() {
        String message = "sample msg";
        // 设置部分请求参数
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
        // 发消息
        rabbitTemplate.send(MQConfig.QUEUE_SAMPLE, new Message(message.getBytes(StandardCharsets.UTF_8), messageProperties));
    }

    // 工作队列模式 (测试用例可能会开启客户端)
    @Test
    public void workQueue() {
        String message = "work queue msg";
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
        // 制造多个消息进行发送操作
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.send(MQConfig.QUEUE_WORK, new Message(message.getBytes(StandardCharsets.UTF_8), messageProperties));
        }
    }

    // pub/sub 发布订阅模式   交换机类型 fanout
    @Test
    public void fanoutSend() {
        String message = "fanout msg";
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
        // fanout模式只往exchange里发送消息。分发到exchange下的所有queue
        rabbitTemplate.send(MQConfig.EXCHANGE_FANOUT, "", new Message(message.getBytes(StandardCharsets.UTF_8), messageProperties));
    }

    // routing路由工作模式  交换机类型 direct
    @Test
    public void routingSend() {
        String routingKey = MQConfig.ROUTE_KEY_CHANGSHA;
        String message = "routing msg";
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
        // fanout模式只往exchange里发送消息。分发到exchange下的所有queue
        rabbitTemplate.send(MQConfig.EXCHANGE_DIRECT, routingKey, new Message(message.getBytes(StandardCharsets.UTF_8), messageProperties));
    }

    // topic 工作模式   交换机类型 topic
    @Test
    public void topicSend() {
        // String ROUTE_KEY_REG_1 = "changsha.*";
        // String ROUTE_KEY_REG_2 = "#.beijing";
        String routingKey = "changsha.dev";
        String message = "topic msg";
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
        // fanout模式只往exchange里发送消息。分发到exchange下的所有queue
        rabbitTemplate.send(MQConfig.EXCHANGE_TOPIC, routingKey, new Message(message.getBytes(StandardCharsets.UTF_8), messageProperties));
    }
}
