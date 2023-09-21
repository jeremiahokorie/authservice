package com.authservice.dto.request;

import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private String email;
    private String phone;
    private String password;
    private String role;
}
