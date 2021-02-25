package com.zw.lb.configuration;

import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhangwei
 * @since 1.0.0
 */

public class ServiceRibbonConfiguration {
    /**
     * 全局配置复杂均衡
     * @return
     */
    @Bean
    public IRule bestAvailableRule() {
        // 过滤性能最差的
        return new BestAvailableRule();
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
