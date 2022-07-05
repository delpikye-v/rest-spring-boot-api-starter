package com.dp.spring.template.controllers.base;

import com.dp.spring.template.common.UserContext;
import com.dp.spring.template.common.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected UserContext getUserContext() {
        return UserContextHolder.getContext();
    }
}
