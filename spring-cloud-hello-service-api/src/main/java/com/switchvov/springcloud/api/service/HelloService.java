package com.switchvov.springcloud.api.service;

import com.switchvov.springcloud.api.model.User;
import org.springframework.web.bind.annotation.*;

/**
 * Hello Service API
 * Created by Switch on 2017/7/9.
 */
@RequestMapping("/refactor")
public interface HelloService {
    @RequestMapping(value = "/hello4", method = RequestMethod.GET)
    String hello(@RequestParam("name") String name);

    @RequestMapping(value = "/hello5", method = RequestMethod.GET)
    User hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age);

    @RequestMapping(value = "/hello6", method = RequestMethod.POST)
    String hello(@RequestBody User user);
}
