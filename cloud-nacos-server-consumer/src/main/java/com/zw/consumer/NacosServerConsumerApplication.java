package com.zw.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhangwei
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosServerConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosServerConsumerApplication.class, args);
    }
}
