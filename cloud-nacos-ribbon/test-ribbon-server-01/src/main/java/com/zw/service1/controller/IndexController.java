package com.zw.service1.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangwei
 * @since 1.0.0
 */
@RestController
public class IndexController {
    @Value("${spring.application.name}")
    private String appName;
    @Value("${server.port}")
    private String port;

    @GetMapping("/")
    public String index() {
        return appName +": "+ port;
    }
}
