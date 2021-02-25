package com.zw.feign.service;

import com.zw.feign.fallback.FeignExampleServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author zhangwei
 */
@FeignClient(value = "nacos-server-provider",
fallback = FeignExampleServiceFallBack.class)
public interface FeignExampleService {
    /**
     * 测试feign
     * @return
     */
    @GetMapping(value = "/")
    String getData();
}


