package com.switchvov.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 本地跳转Controller
 *
 * @Author Switch
 * @Date 2017/8/31
 */
@RestController
@RequestMapping("/local")
public class LocalController {
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World Local";
    }
}
