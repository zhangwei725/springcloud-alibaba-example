package com.cloudalibaba.dubbo.api.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {
    private Long uid;
    private String name;
    private Integer age;
}

