package com.switchvov.springcloud.ribbontest.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.switchvov.springcloud.ribbontest.model.User;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

import java.util.concurrent.Future;

/**
 * @Author Switch
 * @Date 2017/8/27
 */
public class UserCommand extends HystrixCommand<User> {

    private RestTemplate restTemplate;
    private Long id;

    protected UserCommand(Setter setter, RestTemplate restTemplate, Long id) {
        super(setter);
        this.restTemplate = restTemplate;
        this.id = id;
    }

    @Override
    protected User run() throws Exception {
        return restTemplate.getForObject("http://USER-SERVICE/users/{l}", User.class, id);
    }

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        Setter setter = Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("users"));
        UserCommand command = new UserCommand(setter, restTemplate, 1L);
        // 同步执行
        User user = command.execute();
        // 异步执行
        Future<User> futureUser = command.queue();
        // 响应式Hot Observable
        Observable<User> hotObserver = command.observe();
        // 响应式Cold Observable
        Observable<User> coldObserver = command.toObservable();
    }
}
