package com.switchvov.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author Switch
 * @Date 2017/8/6
 */
@Service
public class HelloService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallback")
    public String helloService() {
        return restTemplate.getForEntity("http://hello-service/hello", String.class).getBody();
    }

    public String helloFallback() {
        return "error";
    }
}
