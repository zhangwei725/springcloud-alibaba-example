package com.sb.rocketmq.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @program: springcloud-alibaba-example
 * @author：zhangwei
 * @create：2019-11-21 01:12
 * @version: 1.0
 */
@Component
@Slf4j
@RocketMQMessageListener(consumerGroup = "hello-msg", topic = "base-topic")
public class BaseTopicListener implements RocketMQListener<Object> {
    @Override
    public void onMessage(Object message) {
        log.error(message.toString());
    }
}
