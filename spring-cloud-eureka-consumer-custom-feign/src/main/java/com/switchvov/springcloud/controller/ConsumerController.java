package com.switchvov.springcloud.controller;

import com.switchvov.springcloud.model.User;
import com.switchvov.springcloud.service.HelloService;
import com.switchvov.springcloud.service.RefactorHelloService;
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

    @Autowired
    private RefactorHelloService refactorHelloService;

    @RequestMapping(value = "/feign-consumer", method = RequestMethod.GET)
    public String helloConsumer() {
        String body = helloService.hello();
        LOGGER.info("http://hello-service/hello body is " + body);
        return body;
    }

    @RequestMapping(value = "/feign-consumer2", method = RequestMethod.GET)
    public String helloConsumer2() {
        return helloService.hello() + "\n" +
                helloService.hello("Switch") + "\n" +
                helloService.hello("Switch", 21) + "\n" +
                helloService.hello(new User("Switch", 21)) + "\n";
    }

    @RequestMapping(value = "/feign-consumer3", method = RequestMethod.GET)
    public String helloConsumer3() {
        return refactorHelloService.hello("Switch") + "\n" +
                refactorHelloService.hello("Switch", 21) + "\n" +
                refactorHelloService.hello(new com.switchvov.springcloud.api.model.User("Switch", 21)) + "\n";
    }
}
