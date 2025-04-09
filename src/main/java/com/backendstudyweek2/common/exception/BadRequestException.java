package com.backendstudyweek2.common.exception;

import lombok.Getter;

@Getter
public class BadRequestException extends BaseException {

    public static final int STATUS_CODE = 400;

    public BadRequestException(String message) {
        super(STATUS_CODE, message);
    }
}
