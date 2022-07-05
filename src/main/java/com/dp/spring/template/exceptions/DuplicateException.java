package com.dp.spring.template.exceptions;

public class DuplicateException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DuplicateException() {
    }

    public DuplicateException(String message) {
        super(message);
    }
}
