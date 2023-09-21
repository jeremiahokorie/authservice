package com.authservice.service;

import com.authservice.dto.UserDto;
import com.authservice.dto.request.UserRequest;
import com.authservice.entity.User;

public interface UserService {
    UserDto create(UserRequest user);
}
