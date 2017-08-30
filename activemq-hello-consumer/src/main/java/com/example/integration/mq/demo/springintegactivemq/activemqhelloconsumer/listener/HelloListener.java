package com.example.integration.mq.demo.springintegactivemq.activemqhelloconsumer.listener;

import com.example.integration.mq.demo.springintegactivemq.activemqhelloconsumer.domain.Item;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.xml.soap.Text;
import java.io.IOException;
import java.util.List;

@Component
public class HelloListener {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloListener.class);
    
    private ObjectMapper mapper;
    
    @Autowired
    public HelloListener(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    //TODO: get Item domain object working
    @JmsListener(destination = "${activemq.queue.name}")
    public void receive(Message message) throws JMSException {
    
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            try {
                handleMessage(textMessage);
            } catch (Exception e) {
                LOGGER.error("TextMessage handling failed", e);
            }
        } else {
            LOGGER.error("Message is not a text message " + message.toString());
        }
    }
    
    private void handleMessage(TextMessage textMessage) throws JMSException, IOException {
        List<Item> items = mapper.readValue(textMessage.getText(), new TypeReference<List<Item>>(){});
        for (Item item : items) {
            LOGGER.info("TO String: " + item.toString());
        }
    }
    
}
