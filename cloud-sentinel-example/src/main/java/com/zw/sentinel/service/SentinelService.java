package com.zw.sentinel.service;

import com.zw.sentinel.fallback.SentinelServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zhangwei
 */
@FeignClient(value = "nacos-server-provider", fallback = SentinelServiceFallBack.class)
public interface SentinelService {
    @GetMapping("/")
    String getData();
}
