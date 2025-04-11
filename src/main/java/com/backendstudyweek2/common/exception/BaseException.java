package com.backendstudyweek2.common.exception;

import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException {

    private final int code;

    public BaseException(int code, String message) {
        super(message);
        this.code = code;
    }
}
