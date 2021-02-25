package com.sb.redis.example.domain;

import lombok.Builder;
import lombok.Data;

/**
 * @program: springcloud-alibaba-example
 * @author：zhangwei
 * @create：2019-11-17 12:15
 * @version: 1.0
 */
@Data
@Builder
public class RedisObject {
    private Integer id;
    private String name;
}
