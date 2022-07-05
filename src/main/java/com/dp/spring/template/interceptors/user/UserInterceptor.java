package com.dp.spring.template.interceptors.user;

import com.dp.spring.template.interceptors.base.BaseInterceptor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserInterceptor extends BaseInterceptor implements HandlerInterceptor {
	public boolean preHandle(
			HttpServletRequest request,
			HttpServletResponse response,
			Object handler
	) throws Exception {
		logger.info("UserInterceptor preHandle method called");
		return true; 
	}

	public  void postHandle(
			HttpServletRequest request,
			HttpServletResponse response,
			Object handler, @Nullable
					ModelAndView modelAndView
	) throws Exception {
		logger.info("UserInterceptor postHandle method called");
	}
		
	public void afterCompletion(
			HttpServletRequest request,
			HttpServletResponse response,
			Object handler,
			@Nullable Exception ex
	)throws Exception {
		logger.info("UserInterceptor afterCompletion method called");

	}
	

}
