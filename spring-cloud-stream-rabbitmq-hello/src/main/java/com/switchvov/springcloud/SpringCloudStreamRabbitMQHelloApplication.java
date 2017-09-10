package com.switchvov.springcloud;

import com.switchvov.binder.SinkUserSender;
import com.switchvov.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@SpringBootApplication
//@EnableBinding(value = {Sink.class, SinkSender.class})
@EnableBinding(value = {Sink.class, SinkUserSender.class})
public class SpringCloudStreamRabbitMQHelloApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringCloudStreamRabbitMQHelloApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudStreamRabbitMQHelloApplication.class, args);
    }

    // @ServiceActivator(inputChannel = Sink.INPUT)
    public void receive(Object payload) {
        LOGGER.info("Received: " + payload);
    }

    // @ServiceActivator(inputChannel = Sink.INPUT)
    @StreamListener(Sink.INPUT)
    public void receive(User user) {
        LOGGER.info("Received: " + user);
    }
}
