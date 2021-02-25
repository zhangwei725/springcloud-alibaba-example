package com.zw.feign.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * @author zhangwei
 */
public class LoadBalanceService {
    
    @Resource
    DiscoveryClient discoveryClient;

    public String randomTakeUri(String serviceId){
        //获取目标微服务的所有实例的请求地址
        List<String> instances = discoveryClient.getInstances(serviceId).stream().map(i -> i.getUri().toString())
                .collect(Collectors.toList());
        int i = ThreadLocalRandom.current().nextInt(instances.size());
        return instances.get(i);
    }

}



