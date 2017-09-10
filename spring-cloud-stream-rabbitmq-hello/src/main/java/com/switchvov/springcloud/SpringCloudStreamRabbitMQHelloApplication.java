package com.switchvov.springcloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;

@SpringBootApplication
@EnableBinding(Sink.class)
public class SpringCloudStreamRabbitMQHelloApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringCloudStreamRabbitMQHelloApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudStreamRabbitMQHelloApplication.class, args);
    }

    @ServiceActivator(inputChannel = Sink.INPUT)
    public void loggerSink(Object payload) {
        LOGGER.info("Received: " + payload);
    }
}
