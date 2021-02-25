package com.sb.rocketmq.consumer.service;


import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;


/**
 * @program: springcloud-alibaba-example
 * @author：zhangwei
 * @create：2019-11-20 20:58
 * @version: 1.0
 * 分布式事务
 * 一个消费者只能消费tags
 * 如何保证消息不会丢失
 */
@Service
@Slf4j
@RocketMQMessageListener(consumerGroup = "hello", topic = "test-topic")
public class RocketMqConsumerListener implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        log.error(message);
    }
}
