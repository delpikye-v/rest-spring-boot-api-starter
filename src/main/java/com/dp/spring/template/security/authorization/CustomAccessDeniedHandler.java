package com.dp.spring.template.security.authorization;

import com.dp.spring.template.security.BaseAuthenticationEntryPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.FORBIDDEN;

/**
 * AccessDeniedException is catch up by GlobalExceptionHandler in @ControllerAdvice
 */
@Component
public class CustomAccessDeniedHandler extends BaseAuthenticationEntryPoint implements AccessDeniedHandler {
	private static final Logger logger = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

	@Override
	public void handle(
			HttpServletRequest request,
			HttpServletResponse response,
			AccessDeniedException accessDeniedException
	) throws IOException, ServletException {
		logger.error("Access Denied error: {}", accessDeniedException.getMessage());
		this.handleProcess(request, response, accessDeniedException, FORBIDDEN);
	}
}
