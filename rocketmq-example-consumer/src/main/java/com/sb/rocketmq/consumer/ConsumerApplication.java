package com.sb.rocketmq.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * -  顺序消费
 * -  并发消费（广播/集群）
 * -  one-way方式发送
 * -  事务方式发送
 * -  消息轨迹
 * -  ACL
 * -  pull消费
 * -  push消费
 * @author zhangwei
 */
@SpringBootApplication
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

}
