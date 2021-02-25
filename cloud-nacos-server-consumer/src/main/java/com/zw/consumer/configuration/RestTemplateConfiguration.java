package com.zw.consumer.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * --spring.profiles.active=dev/prod/test     # 指定运行环境
 * --server.port=你的端口号
 */
@Configuration
public class RestTemplateConfiguration {
    @Bean
    //RestTemplate在请求时拥有客户端负载均衡的能力
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
