package com.zw.lb.configuration;

import com.netflix.loadbalancer.*;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhangwei
 * @since 1.0.0
 */
@Configuration
public class RibbonConfiguration {
//    @Bean
//    public IRule bestAvailableRule() {
//        // 过滤性能最差的
//        return new WeightedResponseTimeRule();
//    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
