package com.switchvov.springcloud.command.collapser;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.switchvov.springcloud.model.User;

import java.util.List;

/**
 * @Author Switch
 * @Date 2017/8/27
 */
public class UserBatchCommand extends HystrixCommand<List<User>> {
    private UserCollapseService userCollapseService;
    private List<Long> userIds;

    protected UserBatchCommand(UserCollapseService userCollapseService, List<Long> userIds) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("userServiceCommand")));
        this.userCollapseService = userCollapseService;
        this.userIds = userIds;
    }

    @Override
    protected List<User> run() throws Exception {
        return userCollapseService.findAll(userIds);
    }
}
