package com.zw.lb.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author zhangwei
 * @since 1.0.0
 */
@RestController
@Slf4j
public class RibbonController {
    @Resource
    RestTemplate restTemplate;

    @RequestMapping("/service1")
    public String testService1() {
        String port = restTemplate.getForObject("http://test-ribbon-server-01/", String.class);
        log.info("响应的端口是: {}", port);
        return port;
    }

    @RequestMapping("/service2")
    public String testService2() {
        String port = restTemplate.getForObject("http://test-ribbon-server-02/", String.class);
        log.info("响应的端口是: {}", port);
        return port;
    }
}
