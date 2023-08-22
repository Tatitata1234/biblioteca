package com.example.demo.controller.response;

import com.example.demo.domain.Address;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String name;

    private int age;

    private Address address;
}
