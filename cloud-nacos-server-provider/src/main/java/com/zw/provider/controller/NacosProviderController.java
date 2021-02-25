package com.zw.provider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangwei
 */
@RestController
@Slf4j
public class NacosProviderController {
    @Value("${server.port}")
    int port;

    @RequestMapping("/")
    public String test() {
        return "" + port;
    }
}
