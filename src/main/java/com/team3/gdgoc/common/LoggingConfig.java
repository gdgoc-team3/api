package com.team3.gdgoc.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingConfig {
    @Bean
    public RequestAndResponseLoggingFilter requestResponseLoggingFilter() {
        return new RequestAndResponseLoggingFilter();
    }
}
