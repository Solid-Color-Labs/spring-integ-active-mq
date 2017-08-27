package com.example.integration.mq.demo.springintegactivemq.activemqhelloconsumer.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class HelloListener {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloListener.class);

    //TODO: get Item domain object working
    @JmsListener(destination = "${activemq.queue.name}")
    public void receive(Message message) throws JMSException {
        LOGGER.info("MQ message: " + message.toString());
    }
    
}
