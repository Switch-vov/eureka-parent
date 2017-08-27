package com.switchvov.springcloud.command;

import com.netflix.hystrix.HystrixObservableCommand;
import com.switchvov.springcloud.model.User;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;

/**
 * @Author Switch
 * @Date 2017/8/27
 */
public class UserObservableCommand extends HystrixObservableCommand<User> {
    private RestTemplate restTemplate;
    private Long id;

    protected UserObservableCommand(Setter setter, RestTemplate restTemplate, Long id) {
        super(setter);
        this.restTemplate = restTemplate;
        this.id = id;
    }

    @Override
    protected Observable<User> construct() {
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
}
