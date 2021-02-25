package com.zw.sentinel.fallback;

import com.zw.sentinel.service.SentinelService;

public class SentinelServiceFallBack implements SentinelService {
    @Override
    public String getData() {
        return "服务降级---爽YY";
    }
}
