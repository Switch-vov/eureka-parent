package com.switchvov.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;

@EnableZipkinStreamServer
@SpringBootApplication
public class SpringCloudSleuthZipkinStreamRabbitMQServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudSleuthZipkinStreamRabbitMQServerApplication.class, args);
    }
}
