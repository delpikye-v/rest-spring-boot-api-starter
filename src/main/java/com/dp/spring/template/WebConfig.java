package com.dp.spring.template;

import com.dp.spring.template.interceptors.UserContextInterceptor;
import com.dp.spring.template.interceptors.reservation.ReservationInterceptor;
import com.dp.spring.template.interceptors.tutorial.TutorialInterceptor;
import com.dp.spring.template.interceptors.user.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private UserContextInterceptor userContextInterceptor;

	@Autowired
	private TutorialInterceptor tutorialInterceptor;
	
	@Autowired
	private ReservationInterceptor reservationInterceptor;

	@Autowired
	private UserInterceptor userInterceptor;
	
	
   public void addInterceptors(InterceptorRegistry registry) {
	   InterceptorRegistration userContextInterceptorRegistration = registry.addInterceptor(userContextInterceptor);

	   InterceptorRegistration tutorialInterceptorRegistration = registry.addInterceptor(tutorialInterceptor);
	   tutorialInterceptorRegistration.addPathPatterns("/tutorials/**");
	   /*tutorialInterceptorRegistration.excludePathPatterns("/admin/**");*/

	   InterceptorRegistration userInterceptorRegistration = registry.addInterceptor(userInterceptor);
	   userInterceptorRegistration.addPathPatterns("/user/**");
	   /*userInterceptorRegistration.excludePathPatterns("/admin/**");*/

	   InterceptorRegistration reservationInterceptorRegistration = registry.addInterceptor(reservationInterceptor);
	   reservationInterceptorRegistration.addPathPatterns("/reservations/**");
   }

}
