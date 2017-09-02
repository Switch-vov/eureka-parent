package com.switchvov.springcloud;

import com.netflix.zuul.FilterProcessor;
import com.switchvov.springcloud.auth.AccessFilter;
import com.switchvov.springcloud.error.CustomErrorAttributes;
import com.switchvov.springcloud.processor.PostErrorProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class SpringCloudZuulApplication {
    public static void main(String[] args) {
        // 启用自定义核心处理器
        FilterProcessor.setProcessor(new PostErrorProcessor());
        SpringApplication.run(SpringCloudZuulApplication.class, args);
    }

    // 配置鉴权
    @Bean
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }

    //自定义路由映射规则
    // 当符合像service-v1这样的模式时，转换为/v1/service这样的模式
    @Bean
    public PatternServiceRouteMapper serviceRouteMapper() {
        return new PatternServiceRouteMapper(
                "(?<name>^.+)-(?<version>v.+$)",
                "${version}/${name}");
    }

    // 自定义异常信息
    @Bean
    public DefaultErrorAttributes errorAttributes() {
        return new CustomErrorAttributes();
    }
}
