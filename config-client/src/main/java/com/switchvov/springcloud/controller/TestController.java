package com.switchvov.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Switch
 * @Date 2017/9/2
 */
@RefreshScope
@RestController
public class TestController {
    @Value("${from}")
    private String from;

    @Autowired
    private Environment env;

    @RequestMapping("/from")
    public String from() {
        return this.from;
    }

    @RequestMapping("/from/env")
    public String fromEnv() {
        return env.getProperty("from", "undefined");
    }
}
