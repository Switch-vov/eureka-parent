package com.switchvov.springcloud;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringCloudRabbitMQHelloApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudRabbitMQHelloApplication.class, args);
    }

    @Bean
    public Queue helloQueue() {
        return new Queue("hello");
    }
}
