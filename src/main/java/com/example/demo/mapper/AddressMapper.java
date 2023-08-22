package com.example.demo.mapper;

import com.example.demo.controller.response.AddressResponse;
import com.example.demo.domain.Address;

public class AddressMapper {
    private AddressMapper(){
    }
    public static AddressResponse toResponse(Address entity) {
        return AddressResponse.builder()
                .id(entity.getId())
                .description(entity.getDescription())
                .number(entity.getNumber())
                .build();
    }
}
