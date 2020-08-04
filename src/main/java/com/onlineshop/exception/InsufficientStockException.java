package com.onlineshop.exception;

public class InsufficientStockException extends Exception {

    public InsufficientStockException(String errorMessage){
        super(errorMessage);
    }
}
