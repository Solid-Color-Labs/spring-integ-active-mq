package com.example.integration.mq.demo.springintegactivemq.activemqhelloconsumer.listener;

import com.example.integration.mq.demo.springintegactivemq.activemqhelloconsumer.domain.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class HelloListener {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloListener.class);

    @JmsListener(destination = "${activemq.queue.name}")
    public void receive(Message message) {
        try {
            Item item = (Item) converter().fromMessage(message);
            LOGGER.info("MQ message: " + item.toString());
        } catch (JMSException e) {
            LOGGER.error("JMSException", e);
        }
    }

    @Bean
    public MessageConverter converter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        return converter;
    }
    
}
