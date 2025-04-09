package com.backendstudyweek2.common.ui;

import com.backendstudyweek2.common.domain.ErrorDetails;
import com.backendstudyweek2.common.exception.BadRequestException;
import com.backendstudyweek2.common.exception.BaseException;
import com.backendstudyweek2.common.exception.InternalServerException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetails handleBadRequestException(BadRequestException e, HttpServletRequest request) {
        log.warn(e.getMessage(), e);

        return new ErrorDetails(BadRequestException.STATUS_CODE, e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(InternalServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDetails handleInternalServerException(InternalServerException e, HttpServletRequest request) {
        log.error(e.getMessage(), e);

        return new ErrorDetails(InternalServerException.STATUS_CODE, e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDetails handleException(BaseException e, HttpServletRequest request) {
        log.error(e.getMessage(), e);

        return new ErrorDetails(e.getCode(), e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDetails handleException(Exception e, HttpServletRequest request) {
        log.error(e.getMessage(), e);

        return new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), request.getRequestURI());
    }
}
