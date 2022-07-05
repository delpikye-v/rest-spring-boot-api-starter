package com.dp.spring.template.interceptors;

import com.dp.spring.template.common.UserContext;
import com.dp.spring.template.common.UserContextHolder;
import com.dp.spring.template.interceptors.base.BaseInterceptor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Component
public class UserContextInterceptor extends BaseInterceptor implements HandlerInterceptor {
	private void setUserContext(HttpServletRequest request) {
		Locale locale = request.getLocale();
		String language = locale.getLanguage();
		UserContext userContext = new UserContext("userId111", "1", "1", "1", "GMT+7");
		UserContextHolder.setContext(userContext);
		if (locale != null && !LocaleContextHolder.getLocale().equals(locale)) {
			LocaleContextHolder.setLocale(locale);
		}
	}

	public boolean preHandle(
			HttpServletRequest request,
			HttpServletResponse response,
			Object handler
	) throws Exception {
		logger.error("UserContextInterceptor preHandle method called");
		setUserContext(request);
		return true;
	}

	public  void postHandle(
			HttpServletRequest request,
			HttpServletResponse response,
			Object handler, @Nullable
					ModelAndView modelAndView
	) throws Exception {
		logger.error("UserContextInterceptor postHandle method called");
	}
		
	public void afterCompletion(
			HttpServletRequest request,
			HttpServletResponse response,
			Object handler,
			@Nullable Exception ex
	)throws Exception {
		logger.error("UserContextInterceptor afterCompletion method called");
		// Clear UserContext
		UserContextHolder.clearContext();
	}
	

}
