package com.zw.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zhangwei
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class NacosFeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosFeignApplication.class, args);
    }
}
