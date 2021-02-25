package com.cloudalibaba.dubbo.api;

import com.cloudalibaba.dubbo.api.dto.UserDTO;

import java.util.List;


public interface UserApi {
    List<UserDTO> getAllUsers();
}
