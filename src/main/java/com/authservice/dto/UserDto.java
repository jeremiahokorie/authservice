package com.authservice.dto;

import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String email;
    private String phone;
    private String password;
}
