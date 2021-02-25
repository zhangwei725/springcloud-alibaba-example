package com.sb.redis.example;

import com.sb.redis.example.domain.Moments;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

import javax.annotation.Resource;

/**
 * @program: springcloud-alibaba-example
 * @author：zhangwei
 * @create：2019-11-17 12:04
 * @version: 1.0
 */
@SpringBootTest
public class RedisTemplateTests {
    @Resource
    ValueOperations<String, Object> valueOperations;
    @Resource
    HashOperations<String, String, Object> hashOperations;
    @Resource
    ZSetOperations<String, Object> zSetOperations;

    /**
     *
     */
    @Test
    void likeDemo() {
//        保存
        Moments moments;
        moments = Moments.builder()
                .momentsId(1L)
                .content("小区禁止出租淘宝户")
                .build();

        hashOperations.put("moments:" + moments.getMomentsId(), "content", moments.getContent());

        moments = Moments.builder()
                .momentsId(2L)
                .content("林志玲婚礼行头")
                .build();

        hashOperations.put("moments:" + moments.getMomentsId(), "content", moments.getContent());
        moments = Moments.builder()
                .momentsId(3L)
                .content("香港10年来首次经济年度负增长")
                .build();
        hashOperations.put("moments:" + moments.getMomentsId(), "content", moments.getContent());



    }
}
