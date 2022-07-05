package com.dp.spring.template.exceptions;

public class AuthenticationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public AuthenticationException() {
    }

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
