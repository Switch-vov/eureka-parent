package com.switchvov.springcloud.command.crud;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import com.switchvov.springcloud.model.User;
import org.springframework.web.client.RestTemplate;

/**
 * @Author Switch
 * @Date 2017/8/27
 */
public class UserGetCommand extends HystrixCommand<User> {
    private static final HystrixCommandKey COMMAND_KEY = HystrixCommandKey.Factory.asKey("commandName");

    private RestTemplate restTemplate;
    private Long id;


    protected UserGetCommand(RestTemplate restTemplate, Long id) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("usercrud")).andCommandKey(COMMAND_KEY));
        this.restTemplate = restTemplate;
        this.id = id;
    }

    @Override
    protected User run() throws Exception {
        return restTemplate.getForObject("http://USER-SERVICE/users/{l}", User.class, id);
    }

    // 请求缓存
    @Override
    protected String getCacheKey() {
        // 根据id存入缓存
        return String.valueOf(id);
    }

    public static void flushCache(Long id) {
        // 刷新缓存，根据id清理
        HystrixRequestCache.getInstance(COMMAND_KEY, HystrixConcurrencyStrategyDefault.getInstance()).clear(String.valueOf(id));
    }
}
