package com.smart.jwt.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangwei
 */
@Configuration
@EnableConfigurationProperties(JwtConfig.class)
public class ShiroJwtConfig {

}
