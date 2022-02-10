package com.cn.config;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//创建mq配置
@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_INFORM_EMAIL = "ws_queue";
    public static final String EXCHANGE_TOPICS_INFORM = "ws_exchange";

    @Value("${netty.port}")
    private int port;

    //创建队列
    @Bean
    public Queue queue() {
        return new Queue(QUEUE_INFORM_EMAIL+"_"+port);
    }

    //创建交换机
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(EXCHANGE_TOPICS_INFORM);
    }

    //队列绑定交换机
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(fanoutExchange());
    }

}
