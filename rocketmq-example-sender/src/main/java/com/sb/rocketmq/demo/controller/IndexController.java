package com.sb.rocketmq.demo.controller;

import com.sb.rocketmq.demo.service.RocketMqServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: springcloud-alibaba-example
 * @author：zhangwei
 * @create：2019-11-21 00:54
 * @version: 1.0
 */
@RestController
public class IndexController {
    @Resource
    RocketMqServiceImpl rocketMqService;

    @RequestMapping("/")
    public String index() {
        rocketMqService.sendBaseMsg("hello");
        return "测试消息队列";
    }
}
