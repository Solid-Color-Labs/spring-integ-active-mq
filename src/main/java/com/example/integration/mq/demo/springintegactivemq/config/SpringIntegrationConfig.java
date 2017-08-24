package com.example.integration.mq.demo.springintegactivemq.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.jdbc.JdbcPollingChannelAdapter;
import org.springframework.integration.jms.DynamicJmsTemplate;
import org.springframework.integration.jms.JmsSendingMessageHandler;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.util.ErrorHandler;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.sql.DataSource;
import java.util.Collections;

/*
PeriodicTrigger setFixedRate(true); docs
_________________________________________
        Specify whether the periodic interval should be
        measured between the scheduled start times rather
        than between actual completion times. The latter,
        "fixed delay" behavior, is the default.
        
        Set to false if expecting polling completion time
        of more than interval.
        
        https://docs.spring.io/spring-integration/reference/html/jdbc.html
        https://springframework.guru/spring-boot-example-of-spring-integration-and-activemq/
        http://www.devglan.com/spring-boot/spring-boot-jms-activemq-example
         */
@Configuration
public class SpringIntegrationConfig {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringIntegrationConfig.class);
    
    @Value("${database.polling-interval.rate-in-milliseconds}")
    private Long pollingRateInMilliSeconds;
    
    @Bean
    public PollerMetadata poller(PlatformTransactionManager transactionManager) {
        PeriodicTrigger trigger = new PeriodicTrigger(pollingRateInMilliSeconds);
        trigger.setFixedRate(true);
    
        MatchAlwaysTransactionAttributeSource attributeSource = new MatchAlwaysTransactionAttributeSource();
        attributeSource.setTransactionAttribute(new DefaultTransactionAttribute());
        TransactionInterceptor interceptor = new TransactionInterceptor(transactionManager, attributeSource);
        
        PollerMetadata poller = new PollerMetadata();
        poller.setTrigger(trigger);
        poller.setAdviceChain(Collections.singletonList(interceptor));
        poller.setErrorHandler(new ErrorHandler() {
            @Override
            public void handleError(Throwable throwable) {
                LOGGER.error("Poller failed.", throwable);
            }
        });
        return  poller;
    }
    
    @Bean
    @InboundChannelAdapter(value = "helloWorldChannel", channel = "helloWorldChannel", poller = @Poller("poller"))
    public MessageSource<?> helloWorldMessageSource(DataSource dataSource) {
        JdbcPollingChannelAdapter adapter = new JdbcPollingChannelAdapter(dataSource, "select * from item where status = 2");
        adapter.setUpdateSql("update item set status = 10 where id in (:id)"); //update marks items as polled so they don't show up in next poll
        return adapter; //TODO: maybe set rowmapper? Or will it automatically map?
    }
    
    @Bean
    @ServiceActivator(inputChannel = "helloWorldChannel")
    public MessageHandler jsmOutboundAdapter(JmsTemplate template, Queue queue) {
        JmsSendingMessageHandler handler = new JmsSendingMessageHandler(template);
        handler.setDestination(queue);
        return handler;
    }
    
//    @Bean
//    @ServiceActivator(inputChannel = "helloWorldChannel")
//    public MessageHandler jsmOutboundAdapter(ConnectionFactory connectionFactory, Queue queue) {
//        JmsTemplate template = new DynamicJmsTemplate();
//        template.setConnectionFactory(connectionFactory);
//        JmsSendingMessageHandler handler = new JmsSendingMessageHandler(template);
//        handler.setDestination(queue);
//        return handler;
//    }
    
    //TODO: create outbound channeladapter, which will be ActiveMQ jms queue
    
}
