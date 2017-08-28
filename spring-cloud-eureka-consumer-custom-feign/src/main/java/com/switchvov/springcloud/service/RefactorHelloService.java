package com.switchvov.springcloud.service;

import com.switchvov.springcloud.api.service.HelloService;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @Author Switch
 * @Date 2017/8/28
 */
@FeignClient(value = "hello-service")
public interface RefactorHelloService extends HelloService {
}
