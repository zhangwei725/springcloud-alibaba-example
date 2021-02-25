package com.cloudalibaba.dubbo.consumer.controller;

import com.cloudalibaba.dubbo.api.UserApi;
import com.cloudalibaba.dubbo.api.dto.UserDTO;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DubboController {
    @Reference
    private UserApi userApi;

    @RequestMapping("/")
    public List<UserDTO> hello() {
        return userApi.getAllUsers();
    }

}
