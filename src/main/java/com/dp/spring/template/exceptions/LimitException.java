package com.dp.spring.template.exceptions;

public class LimitException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public LimitException() {
    }

    public LimitException(String message) {
        super(message);
    }
}
