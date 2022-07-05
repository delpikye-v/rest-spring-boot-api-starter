package com.dp.spring.template.interceptors.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BaseInterceptor {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
}
