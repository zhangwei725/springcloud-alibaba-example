package com.sb.rocketmq.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zhangwei
 */
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
    @Resource
    RocketMQTemplate template;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        template.convertAndSend("test-topic", "Hello, World!");
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class OrderPaidEvent implements Serializable {
        private String orderId;
        private BigDecimal paidMoney;
    }
}
