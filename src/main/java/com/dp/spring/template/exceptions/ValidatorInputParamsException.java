package com.dp.spring.template.exceptions;

public class ValidatorInputParamsException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String paramName;
    private String errorCode;

    public ValidatorInputParamsException(String msg) {
        super(msg);
    }

    public ValidatorInputParamsException(String paramName, String errorCode, String msg) {
        this(msg);
        this.paramName = paramName;
        this.errorCode = errorCode;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
