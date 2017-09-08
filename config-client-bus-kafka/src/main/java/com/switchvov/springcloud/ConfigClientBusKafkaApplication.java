package com.switchvov.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConfigClientBusKafkaApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientBusKafkaApplication.class, args);
    }
}
