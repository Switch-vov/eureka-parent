package com.switchvov.springcloud.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "hello")
public class Receiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    @RabbitHandler
    public void process(String hello) {
        LOGGER.info("Receiver : " + hello);
    }
}
