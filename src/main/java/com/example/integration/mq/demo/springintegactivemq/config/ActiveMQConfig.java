package com.example.integration.mq.demo.springintegactivemq.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.jms.DynamicJmsTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Queue;

//http://www.devglan.com/spring-boot/spring-boot-jms-activemq-example
@Configuration
public class ActiveMQConfig {
    
    @Value("${activemq.queue.name}")
    private String helloQueue;
    
    @Bean
    public Queue helloJMSQueue() {
        return new ActiveMQQueue(helloQueue);
    }
    
//    @Bean
//    public JmsTemplate template(ConnectionFactory connectionFactory) {
//        JmsTemplate template = new DynamicJmsTemplate();
//        template.setConnectionFactory(connectionFactory);
//        template.setMessageConverter(jsonJmsMessageConverter());
//        return template;
//    }
//
//    //TODO: How do I get datasource and convert to JSON?
//    @Bean // Serialize message content to json using TextMessage
//    public MessageConverter jsonJmsMessageConverter() {
//        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
//        converter.setTargetType(MessageType.TEXT);
//        converter.setTypeIdPropertyName("_type");
//        return converter;
//    }
    
//    @Bean
//    public ConnectionFactory connectionFactory() throws JMSException {
//        ConnectionFactory factory = new ActiveMQConnectionFactory();
//        factory.createConnection();
//        return factory;
//    }
    
}
