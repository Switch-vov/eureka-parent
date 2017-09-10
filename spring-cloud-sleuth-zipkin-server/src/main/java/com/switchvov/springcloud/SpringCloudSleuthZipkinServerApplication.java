package com.switchvov.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

@EnableZipkinServer
@SpringBootApplication
public class SpringCloudSleuthZipkinServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudSleuthZipkinServerApplication.class, args);
    }
}
