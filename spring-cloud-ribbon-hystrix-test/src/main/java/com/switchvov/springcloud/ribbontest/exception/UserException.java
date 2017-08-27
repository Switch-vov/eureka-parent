package com.switchvov.springcloud.ribbontest.exception;

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
