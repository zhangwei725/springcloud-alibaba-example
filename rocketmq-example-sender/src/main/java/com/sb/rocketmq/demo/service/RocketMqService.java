package com.sb.rocketmq.demo.service;

import com.sb.rocketmq.demo.dto.Product;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: springcloud-alibaba-example
 * @author：zhangwei
 * @create：2019-11-20 00:04
 * @version: 1.0
 */

public interface RocketMqService {
    /**
     * @param msg
     */
    void sendBaseMsg(String msg);

    void sendBaseMsgObject(List<Product> products);

    void sendBaseMsgOrder(List<Product> products);
}
