package com.switchvov.springcloud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * trace调用端
 * Created by Switch on 2017/9/10.u
 */
@RestController
public class TraceInvokerController {
    private final Logger LOGGER = LoggerFactory.getLogger(TraceInvokerController.class);

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/trace-1", method = RequestMethod.GET)
    public String trace() {
        LOGGER.info("=== call trace-1 ===");
        return restTemplate.getForEntity("http://trace-2/trace-2", String.class).getBody();
    }
}
