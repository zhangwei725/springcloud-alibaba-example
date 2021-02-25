package com.sb.redis.example;

import com.sb.redis.example.domain.RedisObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.time.Duration;

/**
 * @program: springcloud-alibaba-example
 * @author：zhangwei
 * @create：2019-11-17 12:04
 * @version: 1.0
 */
@SpringBootTest
public class ValueOperationsTests {
    @Resource
    ValueOperations<String, Object> valueOperations;

    @Test
    void operationString() {
        valueOperations.set("hello", "world");
    }

    @Test
    void operationObject() {
        valueOperations.set("obj", RedisObject.builder().id(1).name("测试保存对象").build());
    }

    @Test
    void operationNx() {
        // 如果key不存在 才去设置值 否则不设置
        valueOperations.setIfAbsent("nx", RedisObject.builder().id(1).name("设置过期时间1111").build(), Duration.ofDays(1));
//        如果key存在去设置值 否则不设置值
        valueOperations.setIfPresent("nx", RedisObject.builder().id(1).name("设置过期时间1111").build(), Duration.ofDays(1));
        // 存在才设置值
        valueOperations.setIfPresent("nx1", RedisObject.builder().id(1).name("key不存在则不设置").build(), Duration.ofDays(1));
    }


    @Test
    void operationBitmap() {
        valueOperations.setBit("user:uid:1",0,true);
        valueOperations.get("user:uid:1");
    }

}
