package com.backendstudyweek2.common.exception;

import lombok.Getter;

@Getter
public class InternalServerException extends BaseException {

    public static final int STATUS_CODE = 500;

    public InternalServerException(String message) {
        super(STATUS_CODE, message);
    }
}
