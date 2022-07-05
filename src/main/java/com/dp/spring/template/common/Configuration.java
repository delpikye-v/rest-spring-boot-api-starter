package com.dp.spring.template.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class Configuration {
    @Value("${app.jwtSecret}")
    private String appJwtSecret;
    @Value("${app.jwtExpirationMs}")
    private String appJwtExpiration;
}
