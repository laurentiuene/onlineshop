package com.onlineshop.exception;

import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(CommonExceptionHandler.class);

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<Object> noBalance(final InsufficientBalanceException e) {
        logger.error("User doesn't have enough balance to finish this order!", e);
        return (e != null ? errorResponse(e.getMessage()) : errorResponse("Unknown error"));
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<Object> noStock(final InsufficientStockException e) {
        logger.error("There is no sufficient stock available for this order!", e);
        return (e != null ? errorResponse(e.getMessage()) : errorResponse("Unknown error"));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> notFound(final NotFoundException e) {
        logger.error("The information needed not found!", e);
        return (e != null ? errorResponse(e.getMessage()) : errorResponse("Unknown error"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exception(final Exception e) {
        logger.error("Exception during execution of SpringSecurity application", e);
        return (e != null ? errorResponse(e.getMessage()) : errorResponse("Unknown error"));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        return errorResponse(exception.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    private ResponseEntity<Object> errorResponse(String message){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(message));
    }
}

