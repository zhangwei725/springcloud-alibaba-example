package com.sb.rocketmq.demo.service;

import com.sb.rocketmq.demo.dto.Product;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @program: springcloud-alibaba-example
 * @author：zhangwei
 * @create：2019-11-20 00:04
 * @version: 1.0
 * <p>
 * -  同步发送
 * -  同步顺序发送
 * -  异步发送
 * -  异步顺序发送
 */
@Service
public class RocketMqServiceImpl implements RocketMqService {
    @Resource
    private RocketMQTemplate template;
    @Value("${base-topic}")
    private String baseTopic;

    @Override
    public void sendBaseMsg(String msg) {
        template.convertAndSend(baseTopic, msg);
        // 等价
        template.send(baseTopic, MessageBuilder.withPayload("用如家思想学编程").build());
    }

    /**
     * 发送任意对象
     *
     * @param products
     */
    @Override
    public void sendBaseMsgObject(List<Product> products) {
        // template.syncSend(baseTopic, MessageBuilder.withPayload(products).build());
        SendResult sendResult = template.syncSend(baseTopic, products);
    }

    /**
     * String destination
     * Object payload
     * String hashKey
     * long timeout 发送消息超时时间
     *
     * @param products
     */
    @Override
    public void sendBaseMsgOrder(List<Product> products) {
        template.syncSendOrderly(baseTopic, products, "123");
    }

    public void asyncSendBaseMsgOrder(List<Product> products) {
        template.asyncSendOrderly(baseTopic, products, "1234", new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {

            }

            @Override
            public void onException(Throwable e) {

            }
        });

    }


}
