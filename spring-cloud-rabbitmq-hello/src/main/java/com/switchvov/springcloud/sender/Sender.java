package com.switchvov.springcloud.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Sender {
    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hello " + new Date();
        LOGGER.info("Sender: " + context);
        rabbitTemplate.convertAndSend(context);
    }
}
