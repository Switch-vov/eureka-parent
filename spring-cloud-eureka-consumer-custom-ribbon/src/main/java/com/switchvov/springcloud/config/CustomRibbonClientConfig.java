package com.switchvov.springcloud.config;

import com.netflix.loadbalancer.*;
import com.switchvov.springcloud.annotation.ExcludeComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Switch
 * @Date 2017/8/6
 */
@Configuration
@ExcludeComponentScan
public class CustomRibbonClientConfig {

    @Bean
    public IRule ribbonRule() {
        // return new RandomRule();
        // return new WeightedResponseTimeRule();
        return new BestAvailableRule();
    }
}
