package com.sb.redis.example.controller;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: springcloud-alibaba-example
 * @author：zhangwei
 * @create：2019-11-17 22:33
 * @version: 1.0
 */
@RestController
public class CacheController {
    /**
     * 当有多个参数时，默认就使用多个参数来做key，如果只需要其中某一个参数做key，则可以在@Cacheable注解中，通过key属性来指定key，如上代码就表示只使用id作为缓存的key，如果对key有复杂的要求，可以自定义keyGenerator。
     * 当然，Spring Cache 中提供了root对象，
     * 可以在不定义keyGenerator的情况下实现一些复杂的效果
     */
//    @Cacheable(cacheNames = "index",key = "#name")
    @RequestMapping("/")
    @Cacheable(cacheNames = "index", key = ("targetClass.getName() + '.'+methodName"))
    public String index() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    @CachePut(value = "index", key = ("targetClass.getName() + '.'+methodName"))
    public String updateDate() {
        return "更新数据";
    }

    @CacheEvict
    public boolean deleteDate() {
        return true;
    }

    @RequestMapping("/cache")
    @Caching
    public String testCache() {
        return "";
    }

}
