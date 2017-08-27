package com.switchvov.springcloud.ribbontest.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import com.switchvov.springcloud.ribbontest.exception.UserException;
import com.switchvov.springcloud.ribbontest.model.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.Future;

@Service
public class UserService {
    @Autowired
    private RestTemplate restTemplate;

    // 同步执行
    @HystrixCommand
    public User getUserById(Long id) {
        return restTemplate.getForObject("http://USER-SERVICE/users/{l}", User.class, id);
    }

    // 异步执行
    @HystrixCommand
    public Future<User> getUserByIdAsync(final String id) {
        return new AsyncResult<User>() {
            @Override
            public User invoke() {
                return restTemplate.getForObject("http://USER-SERVICE/users/{l}", User.class, id);
            }
        };
    }

    // 如果已经使用了cacheKeyMethod指定缓存Key的生成函数，那么@CacheKey注解不会生效。
    @CacheResult(cacheKeyMethod = "getUserByIdCacheKey")
    @HystrixCommand(fallbackMethod = "fallback", groupKey = "switch_group",
            commandKey = "get_user", threadPoolKey = "switch_pool", ignoreExceptions = UserException.class,
            observableExecutionMode = ObservableExecutionMode.EAGER)
    public User getById(@CacheKey(value = "id") User user) {
        return restTemplate.getForObject("http://USER-SERVICE/users/{l}", User.class, user.getId());
    }

    @CacheRemove(commandKey = "get_user")
    @HystrixCommand
    public void update(@CacheKey("id") User user) {
        restTemplate.postForObject("http://USER-SERVICE/users", user, User.class);
    }

    private User fallback() {
        return new User();
    }

    private Long getUserByIdCacheKey(User user) {
        return user.getId();
    }


    @HystrixCollapser(batchMethod = "findAll", collapserProperties = @HystrixProperty(name = "timerDelayInMilliseconds", value = "100"))
    public User find(Long id) {
        return null;
    }

    public List<User> findAll(List<Long> ids) {
        return restTemplate.getForObject("http://USER-SERVICE/users?ids={1}", List.class, StringUtils.join(ids, ","));
    }
}
