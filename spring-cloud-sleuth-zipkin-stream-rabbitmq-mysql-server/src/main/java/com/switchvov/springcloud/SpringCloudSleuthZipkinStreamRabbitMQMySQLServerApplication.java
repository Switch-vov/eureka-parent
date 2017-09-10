package com.switchvov.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;

@EnableZipkinStreamServer
@SpringBootApplication
public class SpringCloudSleuthZipkinStreamRabbitMQMySQLServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudSleuthZipkinStreamRabbitMQMySQLServerApplication.class, args);
    }
}
