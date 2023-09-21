package com.authservice.service.impl;

import com.authservice.dto.UserDto;
import com.authservice.dto.request.UserRequest;
import com.authservice.entity.User;
import com.authservice.repository.UserRepository;
import com.authservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService {


    UserRepository userRepository;

    @Override
    public UserDto create(UserRequest userRequest) {
        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setName(userRequest.getName());
        user.setPhone(userRequest.getPhone());
        user.setPassword(new BCryptPasswordEncoder().encode(userRequest.getPassword()));
        User users =  userRepository.save(user);
        UserDto userDto = new UserDto();
        userDto.setEmail(users.getEmail());
        userDto.setName(users.getName());
        userDto.setPhone(users.getPhone());
        return userDto;
    }
}
