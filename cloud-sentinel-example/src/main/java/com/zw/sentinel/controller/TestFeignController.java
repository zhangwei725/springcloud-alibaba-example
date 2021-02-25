package com.zw.sentinel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestFeignController {

    @GetMapping("/test-feign/{id}")
    public String test(@PathVariable Integer id) {
        return "";
    }
}