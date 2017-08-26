package com.example.integration.mq.demo.springintegactivemq.activemqhelloconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class ActivemqHelloConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivemqHelloConsumerApplication.class, args);
	}
}
