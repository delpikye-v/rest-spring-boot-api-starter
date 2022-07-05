package com.dp.spring.template.exceptions;

import com.dp.spring.template.utils.ExceptionUtil;
import com.dp.spring.template.utils.JsonResponse;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;

/**
 * Default Exception HTTP Status Code: 500 - INTERNAL_SERVER_ERROR
 * There are some HTTP Status Code:
 *    400 - BAD_REQUEST
 *    401 - UNAUTHORIZED
 *    403 - FORBIDDEN
 *    404 - NOT_FOUND
 *    413 - PAYLOAD_TOO_LARGE
 *    ...
 * **/
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<JsonResponse> handleGlobalException(Exception ex, WebRequest request) {
        return ExceptionUtil.toResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({NotFoundException.class, FileNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<JsonResponse> handleNoHandlerFoundException(NotFoundException ex, WebRequest request) {
        return ExceptionUtil.toResponseEntity(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MultipartException.class)
    @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
    public ResponseEntity<JsonResponse> handleMultipartException(MultipartException ex, WebRequest request) {
        return ExceptionUtil.toResponseEntity(ex, HttpStatus.PAYLOAD_TOO_LARGE);
    }

    @ExceptionHandler({
            TypeMismatchException.class,
            ValidatorInputParamsException.class,
            HttpMessageNotReadableException.class,
            MethodArgumentNotValidException.class,
            LimitException.class,
            DuplicateException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<JsonResponse> handleParamException(Exception ex, WebRequest request) {
        return ExceptionUtil.toResponseEntity(ex, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({AuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<JsonResponse> handleAuthenticationException(AuthenticationException ex, WebRequest request) {
        return ExceptionUtil.toResponseEntity(ex, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<JsonResponse> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        return ExceptionUtil.toResponseEntity(ex, HttpStatus.FORBIDDEN);
    }
}
