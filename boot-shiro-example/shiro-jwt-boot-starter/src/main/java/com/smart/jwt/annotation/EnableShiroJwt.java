package com.smart.jwt.annotation;

import com.smart.jwt.config.ShiroJwtAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author zhangwei
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({ShiroJwtAutoConfiguration.class})
@Documented
@Inherited
public @interface EnableShiroJwt {

}