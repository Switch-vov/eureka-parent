package com.switchvov.springcloud.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.switchvov.springcloud.model.User;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

import java.util.concurrent.Future;

/**
 * @Author Switch
 * @Date 2017/8/27
 */
public class UserCommand extends HystrixCommand<User> {
    private static final HystrixCommandGroupKey GROUP_KEY = HystrixCommandGroupKey.Factory.asKey("groupName");
    private static final HystrixCommandKey COMMAND_KEY = HystrixCommandKey.Factory.asKey("commandName");
    private static final HystrixThreadPoolKey THREAD_POOL_KEY = HystrixThreadPoolKey.Factory.asKey("threadPoolKey");

    private RestTemplate restTemplate;
    private Long id;

    protected UserCommand(Setter setter, RestTemplate restTemplate, Long id) {
        super(setter);
        this.restTemplate = restTemplate;
        this.id = id;
    }

    // Hystrix默认使用类名作为命令分组
    // Hystrix会根据分组来组织和统计命令的告警、仪表盘等信息
    // Hystrix命令的默认线程划分是根据命令分组实现的，默认相同组名使用同一个线程池
    public UserCommand() {
        super(Setter.withGroupKey(GROUP_KEY).andCommandKey(COMMAND_KEY).andThreadPoolKey(THREAD_POOL_KEY));
    }

    @Override
    protected User run() throws Exception {
        return restTemplate.getForObject("http://USER-SERVICE/users/{l}", User.class, id);
    }

    // 服务降级
    @Override
    protected User getFallback() {
        // 获取异常
        Throwable exception = getExecutionException();
        return new User();
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
