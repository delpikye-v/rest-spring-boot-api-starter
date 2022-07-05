package com.dp.spring.template.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
public class WebApplicationContextHolder implements ApplicationContextAware {
    private static WebApplicationContext wac;

    public static WebApplicationContext getWebApplicationContext() {
        return wac;
    }

    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        wac = (WebApplicationContext) ac;
    }
}
