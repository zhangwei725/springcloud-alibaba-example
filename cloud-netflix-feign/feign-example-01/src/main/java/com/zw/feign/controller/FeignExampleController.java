package com.zw.feign.controller;

import com.zw.feign.service.FeignExampleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhangwei
 */
@RestController
public class FeignExampleController {
    @Resource
    FeignExampleService service;

    @RequestMapping("/")
    public String hello() {
        String s = service.getData();
        return s;
    }
}
