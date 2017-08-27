package com.switchvov.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import com.switchvov.springcloud.exception.UserException;
import com.switchvov.springcloud.model.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;

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

    // 响应式执行
    @HystrixCommand(observableExecutionMode = ObservableExecutionMode.EAGER)
    // @HystrixCommand(observableExecutionMode = ObservableExecutionMode.LAZY)
    public Observable<User> getUserById(final String id) {
        return Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> observer) {
                try {
                    if (!observer.isUnsubscribed()) {
                        User user = restTemplate.getForObject("http://USER-SERVICE/users/{l}", User.class, id);
                        observer.onNext(user);
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        });
    }

    // 降级处理
    // 指定组名、命令名、线程池名
    // 异常传播
    // 缓存，如果已经使用了cacheKeyMethod指定缓存Key的生成函数，那么@CacheKey注解不会生效。
    @CacheResult(cacheKeyMethod = "getUserByIdCacheKey")
    @HystrixCommand(fallbackMethod = "fallback",
            groupKey = "switch_group", commandKey = "get_user", threadPoolKey = "switch_pool",
            ignoreExceptions = UserException.class)
    public User getById(@CacheKey(value = "id") User user) {
        return restTemplate.getForObject("http://USER-SERVICE/users/{l}", User.class, user.getId());
    }

    // 删除缓存
    @CacheRemove(commandKey = "get_user")
    @HystrixCommand
    public void update(@CacheKey("id") User user) {
        restTemplate.postForObject("http://USER-SERVICE/users", user, User.class);
    }

    // 如果降级逻辑不稳定，可以在上面加@HystrixCommand(fallbackMethod = "fallback2")
    // 异常获取
    private User fallback(User user, Throwable e) {
        return new User();
    }

    // 获取缓存
    private Long getUserByIdCacheKey(User user) {
        return user.getId();
    }


    // 请求合并
    @HystrixCollapser(collapserKey ="userCollapseCommand", batchMethod = "findAll",
            collapserProperties = @HystrixProperty(name = "timerDelayInMilliseconds", value = "100"))
    public User find(Long id) {
        return null;
    }

    @HystrixCommand
    public List<User> findAll(List<Long> ids) {
        return restTemplate.getForObject("http://USER-SERVICE/users?ids={1}", List.class, StringUtils.join(ids, ","));
    }
}
