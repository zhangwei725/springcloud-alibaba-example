package com.zw.consumer.service.impl;

import com.zw.consumer.service.NacosConsumerService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;


/**
 * @author zhangwei
 */
@Service
public class NacosConsumerServiceImpl implements NacosConsumerService {
    @Resource
    private RestTemplate restTemplate;

    @Override
    public String getData() {
        return restTemplate.getForObject("http://nacos-server-provider/", String.class);
    }
}
