package com.dp.spring.template.utils;

/*import io.swagger.annotations.ApiModel;*/

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/*@ApiModel(value = "JsonResponse", description = "JsonResponse object")*/
public class JsonResponse implements Serializable {
    private static final long serialVersionUID = -5113058462095302509L;
    public static final String DEFAULT_FIELD = "field";
    public static final String DEFAULT_CODE = "code";
    private boolean success;
    private Object data;
    private Map<String, Map<String, String>> errors;// {field: {code: message}}
    private String status;

    public JsonResponse() {
        this(true, null);
    }

    public JsonResponse(BindingResult bindingResult) {
        this(true, null);
        this.addErrors(bindingResult);
    }

    public JsonResponse(boolean success, Object data) {
        this.success = success;
        this.data = data;
        this.errors = new LinkedHashMap<>();
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Map<String, Map<String, String>> getErrors() {
        return errors;
    }

    public void addError(String message) {
        this.addError(DEFAULT_FIELD, DEFAULT_CODE, message);
    }

    public void addError(String code, String message) {
        this.addError(DEFAULT_FIELD, code, message);
    }

    public void addError(String field, String code, String message) {
        this.setSuccess(false);
        Map<String, String> errors = this.errors.get(field);
        if (errors == null) {
            errors = new HashMap<>();
            this.errors.put(field, errors);
        }
        errors.put(code, message);
    }

    public void addErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                addError(fieldError.getField(), fieldError.getCode(), fieldError.getDefaultMessage());
            }
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
