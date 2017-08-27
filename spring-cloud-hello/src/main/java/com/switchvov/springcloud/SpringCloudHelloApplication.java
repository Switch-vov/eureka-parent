package com.switchvov.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudHelloApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudHelloApplication.class, args);
    }
}
