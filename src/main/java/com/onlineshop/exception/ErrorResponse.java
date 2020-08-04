package com.onlineshop.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    String message;

    public ErrorResponse(String errorMessage) {
        message = errorMessage;
    }
}
