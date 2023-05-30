package com.example.springbootlab.common.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
   路由模式|Routing模式   交换机类型：direct
*/
@Configuration
public class DirectConfig {

    //声明队列
    @Bean
    public Queue directQ1() {
        return new Queue(MQConfig.QUEUE_DIRECT_Q1);
    }

    @Bean
    public Queue directQ2() {
        return new Queue(MQConfig.QUEUE_DIRECT_Q2);
    }


    //声明exchange
    @Bean
    public DirectExchange setDirectExchange() {
        return new DirectExchange(MQConfig.EXCHANGE_DIRECT);
    }

    //声明binding，需要声明一个routingKey
    @Bean
    public Binding bindDirectBind1() {
        return BindingBuilder.bind(directQ1()).to(setDirectExchange()).with(MQConfig.ROUTE_KEY_CHANGSHA);
    }

    @Bean
    public Binding bindDirectBind2() {
        return BindingBuilder.bind(directQ2()).to(setDirectExchange()).with(MQConfig.ROUTE_KEY_BEIJING);
    }

}
