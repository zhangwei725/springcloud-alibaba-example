package com.zw.feign.configuration;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangwei
 * @since 1.0.0
 */
@Configuration
public class FeignConfiguration {
    @Bean
    Logger.Level loggerLevel() {
        return Logger.Level.BASIC;
    }
}
