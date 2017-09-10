package com.switchvov.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudSleuthTrace2Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudSleuthTrace2Application.class, args);
    }
}
