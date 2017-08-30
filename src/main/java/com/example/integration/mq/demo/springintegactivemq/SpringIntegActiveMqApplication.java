package com.example.integration.mq.demo.springintegactivemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@EnableIntegration
@SpringBootApplication
public class SpringIntegActiveMqApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringIntegActiveMqApplication.class, args);
	}
}
