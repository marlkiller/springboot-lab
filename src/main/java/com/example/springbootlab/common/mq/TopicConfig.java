package com.example.springbootlab.common.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
Topics模式  交换机类型 topic
* */
@Configuration
public class TopicConfig {

    //声明队列
    @Bean
    public Queue topicQ1() {
        return new Queue(MQConfig.QUEUE_TOPIC_Q1);
    }

    @Bean
    public Queue topicQ2() {
        return new Queue(MQConfig.QUEUE_TOPIC_Q2);
    }


    //声明exchange
    @Bean
    public TopicExchange setTopicExchange() {
        return new TopicExchange(MQConfig.EXCHANGE_TOPIC);
    }

    //声明binding，需要声明一个roytingKey
    @Bean
    public Binding bindTopicReg1() {
        return BindingBuilder.bind(topicQ1()).to(setTopicExchange()).with(MQConfig.ROUTE_KEY_REG_1);
    }

    @Bean
    public Binding bindTopicReg2() {
        return BindingBuilder.bind(topicQ2()).to(setTopicExchange()).with(MQConfig.ROUTE_KEY_REG_2);
    }

}
