package com.dp.spring.template.security;

import com.dp.spring.template.utils.ExceptionUtil;
import com.dp.spring.template.utils.JsonResponse;
import com.dp.spring.template.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Component
public class BaseAuthenticationEntryPoint {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected void handleProcess(
            HttpServletRequest request,
            HttpServletResponse response,
            RuntimeException excception,
            HttpStatus httpStatus
    ) throws IOException {
        response.setStatus(httpStatus.value());
        response.setContentType(APPLICATION_JSON_VALUE);
        ResponseEntity<JsonResponse> jsonResponseResponseEntity = ExceptionUtil.toResponseEntity(excception, httpStatus);
        String jsonResponseResponseEntityString = JsonUtil.toJson(jsonResponseResponseEntity);
        response.getWriter().write(jsonResponseResponseEntityString);
    }
}
