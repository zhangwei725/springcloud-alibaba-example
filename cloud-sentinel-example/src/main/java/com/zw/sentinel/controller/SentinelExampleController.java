package com.zw.sentinel.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangwei
 */
@RestController
@RequestMapping("/sentinel")
public class SentinelExampleController {
    @RequestMapping("/")
    public String hello() {
        return "Hello Sentinel";
    }
}
