package com.example.springbootlab.common.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SampleConfig {

    @Bean
    public Queue setQueue() {
        return new Queue(MQConfig.QUEUE_SAMPLE);
    }
}