package com.example.integration.mq.demo.springintegactivemq.activemqhelloconsumer.listener;

import com.example.integration.mq.demo.springintegactivemq.activemqhelloconsumer.domain.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

@Component
public class HelloListener {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloListener.class);

    //TODO: get Item domain object working
    @JmsListener(destination = "${activemq.queue.name}")
    public void receive(Message message) {
        LOGGER.info("MQ message: " + message.toString());
    }
    
}
