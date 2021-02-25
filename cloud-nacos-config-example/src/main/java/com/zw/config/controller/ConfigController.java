package com.zw.config.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RefreshScope
@RestController
@Slf4j
public class ConfigController {
    @GetMapping("/")
    public String index() {
        return "配置中心";
    }
}
