package com.switchvov.springcloud.controller;

import com.switchvov.springcloud.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消费者
 * Created by Switch on 2017/7/9.
 */
@RestController
public class ConsumerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerController.class);

    @Autowired
    private HelloService helloService;

    @RequestMapping(value = "/ribbon-consumer", method = RequestMethod.GET)
    public String helloConsumer() {
        String body = helloService.helloService();
        LOGGER.info("http://hello-service/hello body is " + body);
        return body;
    }
}
