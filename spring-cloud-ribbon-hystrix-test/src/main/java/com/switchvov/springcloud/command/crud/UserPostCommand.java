package com.switchvov.springcloud.command.crud;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.switchvov.springcloud.model.User;
import org.springframework.web.client.RestTemplate;

/**
 * @Author Switch
 * @Date 2017/8/27
 */
public class UserPostCommand extends HystrixCommand<User> {
    private RestTemplate restTemplate;
    private User user;

    protected UserPostCommand(RestTemplate restTemplate, User user) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("usercrud")));
        this.restTemplate = restTemplate;
        this.user = user;
    }

    @Override
    protected User run() throws Exception {
        // 写操作
        User u = restTemplate.postForObject("http://USER-SERVICE/users", user, User.class);
        // 刷新缓存，亲历缓存中失效的User
        UserGetCommand.flushCache(u.getId());
        return u;
    }
}
