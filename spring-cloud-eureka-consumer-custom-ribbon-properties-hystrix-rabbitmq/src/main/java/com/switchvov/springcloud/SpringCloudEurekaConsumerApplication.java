package com.switchvov.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//@EnableCircuitBreaker
//@EnableDiscoveryClient
//@SpringBootApplication
@SpringCloudApplication
public class SpringCloudEurekaConsumerApplication {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaConsumerApplication.class, args);
    }
}
