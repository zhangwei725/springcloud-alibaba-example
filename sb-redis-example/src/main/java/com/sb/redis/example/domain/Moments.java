package com.sb.redis.example.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * 朋友圈
 *
 * @program: springcloud-alibaba-example
 * @author：zhangwei
 * @create：2019-11-17 12:43
 * @version: 1.0
 */
@Builder
@Data
@Cacheable
public class Moments {
    private Long momentsId;
    private String content;
    private List<String> images;
}
