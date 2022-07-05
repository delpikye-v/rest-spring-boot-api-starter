package com.dp.spring.template.exceptions;

public class FileNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public FileNotFoundException() {
    }

    public FileNotFoundException(String message) {
        super(message);
    }

    public FileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileNotFoundException(Throwable cause) {
        super(cause);
    }
}
