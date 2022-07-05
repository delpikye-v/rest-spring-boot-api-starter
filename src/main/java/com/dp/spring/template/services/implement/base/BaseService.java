package com.dp.spring.template.services.implement.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BaseService {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /*protected void wrapAndThrow(Exception e) {
        if (logger.isErrorEnabled()) {
            logger.error(e.getMessage(), e);
        }
        throw new ServiceException(e);
    }

    protected void throwException() {
        throw new ServiceException();
    }

    protected void throwException(Exception e) {
        throw new ServiceException(e);
    }*/
}
