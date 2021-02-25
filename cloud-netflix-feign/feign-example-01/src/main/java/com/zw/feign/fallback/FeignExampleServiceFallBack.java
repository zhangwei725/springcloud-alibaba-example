package com.zw.feign.fallback;

import com.zw.feign.service.FeignExampleService;
import org.springframework.stereotype.Component;

@Component
public class FeignExampleServiceFallBack implements FeignExampleService {

    @Override
    public String getData() {
        return "fallback";
    }
}
