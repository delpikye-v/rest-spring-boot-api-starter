package com.dp.spring.template.utils;

import com.dp.spring.template.exceptions.DuplicateNameException;
import com.dp.spring.template.exceptions.ValidatorInputParamsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

public class ExceptionUtil {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionUtil.class);
    public static final String ERROR_NAME_FIELD = "name";
    public static final String ERROR_DUPLICATE_FIELD = "duplicate";

    public static ModelAndView toModelAndView(Exception ex) {
        return toModelAndView(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ModelAndView toModelAndView(Exception ex, HttpStatus httpStatus) {
        return toModelAndView(ex, httpStatus, null);
    }

    public static ModelAndView toModelAndView(Exception ex, HttpStatus httpStatus, String errorMessage) {
        JsonResponse jsonResponse = new JsonResponse();
        if (errorMessage == null) {
            errorMessage = ex.getMessage();
        }
        jsonResponse.addError(errorMessage);
        jsonResponse.setStatus(httpStatus.value() + "");
        logger.error(ex.getMessage());

        return new ModelAndView(String.format("/error/%s.tiles", httpStatus.value())).addObject(jsonResponse);
    }


    public static ResponseEntity<JsonResponse> toResponseEntity(Exception ex) {
        return toResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<JsonResponse> toResponseEntity(Exception ex, HttpStatus httpStatus) {
        return toResponseEntity(ex, httpStatus, ex.getMessage());
    }

    public static ResponseEntity<JsonResponse> toResponseEntity(Exception ex, HttpStatus httpStatus, String errorMessage) {
        JsonResponse body = new JsonResponse();

        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException argumentNotValidException = (MethodArgumentNotValidException) ex;
            List<String> errors = argumentNotValidException.getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            body.addErrors(argumentNotValidException.getBindingResult());
        } else if (ex instanceof ValidatorInputParamsException) {
            ValidatorInputParamsException inputParamsException = (ValidatorInputParamsException) ex;
            body.addError(inputParamsException.getParamName(), inputParamsException.getErrorCode(), errorMessage);
        } else if (ex instanceof DuplicateNameException) {
            body.addError(ERROR_NAME_FIELD, ERROR_DUPLICATE_FIELD, errorMessage);
        } else if (ex instanceof AccessDeniedException) {
            httpStatus = HttpStatus.FORBIDDEN;
            body.addError(errorMessage);
        } else {
            body.addError(errorMessage);
        }
        body.setStatus(httpStatus.value() + "");
        logger.error(ex.getMessage(), ex);

        return new ResponseEntity<>(body, httpStatus);
    }

    public static ResponseEntity<JsonResponse> toResponseEntity(BindingResult bindingResult) {
        JsonResponse body = new JsonResponse(bindingResult);
        body.setStatus(HttpStatus.BAD_REQUEST.value() + "");
        logger.error("ValidationError", bindingResult);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
