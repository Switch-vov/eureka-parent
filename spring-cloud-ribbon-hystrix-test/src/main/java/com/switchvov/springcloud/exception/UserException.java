package com.switchvov.springcloud.exception;

import com.netflix.hystrix.exception.HystrixBadRequestException;

/**
 * 用户异常，用于不触发服务降级，会被包裹成@{@link HystrixBadRequestException}
 */
public class UserException extends RuntimeException {
    private String message;

    public UserException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
