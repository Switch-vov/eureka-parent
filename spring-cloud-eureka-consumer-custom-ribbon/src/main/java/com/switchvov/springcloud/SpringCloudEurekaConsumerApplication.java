package com.switchvov.springcloud;

import com.switchvov.springcloud.annotation.ExcludeComponentScan;
import com.switchvov.springcloud.config.CustomRibbonClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;

import static org.springframework.context.annotation.ComponentScan.*;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(excludeFilters = @Filter(type = FilterType.ANNOTATION,value = ExcludeComponentScan.class))
@RibbonClient(name = "hello-service", configuration = CustomRibbonClientConfig.class)
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
