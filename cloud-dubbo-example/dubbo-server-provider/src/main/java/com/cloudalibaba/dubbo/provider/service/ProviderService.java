package com.cloudalibaba.dubbo.provider.service;

import com.cloudalibaba.dubbo.api.UserApi;
import com.cloudalibaba.dubbo.api.dto.UserDTO;
import org.apache.dubbo.config.annotation.Service;

import java.util.ArrayList;
import java.util.List;

//@Service(version = "${dubbo.version}")
@Service
public class ProviderService implements UserApi {

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> users = new ArrayList<>();
        UserDTO userDTO = new UserDTO();
        userDTO.setName("dubbo");
        userDTO.setUid(1L);
        userDTO.setAge(18);
        users.add(userDTO);
        return users;
    }
}