package com.onlineshop.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(CommonExceptionHandler.class);

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String exception(final Throwable throwable) {
        logger.error("Exception during execution of SpringSecurity application", throwable);
        return (throwable != null ? throwable.getMessage() : "Unknown error");
    }
}

