package com.zw.shiro.controller;

import com.zw.shiro.utils.ShiroUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
public class LoginController {
    @PostMapping("/login")
    public String login(String username, String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        ShiroUtils.getSubject().login(token);
        return ShiroUtils.getSession().getId().toString();
    }

    @GetMapping("/list")
    public String list() {
        return "hello";
    }
}
