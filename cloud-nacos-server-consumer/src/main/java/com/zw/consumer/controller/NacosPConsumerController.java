package com.zw.consumer.controller;

import com.zw.consumer.service.NacosConsumerService;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhangwei
 */
@RestController
public class NacosPConsumerController {
    @Resource
    private NacosConsumerService service;

    @RequestMapping("/")
    public String hello() {
        return service.getData();
    }

}
