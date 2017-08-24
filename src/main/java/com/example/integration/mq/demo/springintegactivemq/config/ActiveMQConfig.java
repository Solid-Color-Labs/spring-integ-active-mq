package com.example.integration.mq.demo.springintegactivemq.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;

@Configuration
public class ActiveMQConfig {
    
    @Value("${activemq.queue.name}")
    private String helloQueue;
    
    @Bean
    public Queue helloJMSQueue() {
        return new ActiveMQQueue(helloQueue);
    }
    
//    @Bean
//    public ActiveMQConnectionFactory connectionFactory() {
//        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
//        return factory;
//    }
    
}
