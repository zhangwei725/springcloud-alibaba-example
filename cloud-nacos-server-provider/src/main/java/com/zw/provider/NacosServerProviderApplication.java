package com.zw.provider;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhangwei
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosServerProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosServerProviderApplication.class, args);
    }
}
