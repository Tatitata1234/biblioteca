package com.example.demo.mapper;

import com.example.demo.controller.response.UserResponse;
import com.example.demo.domain.User;

public class UserMapper {
    private UserMapper(){
    }
    public static UserResponse toResponse(User entity) {
        return UserResponse.builder()
                .id(entity.getId())
                .age(entity.getAge())
                .address(entity.getAddress())
                .name(entity.getName())
                .build();
    }
}
