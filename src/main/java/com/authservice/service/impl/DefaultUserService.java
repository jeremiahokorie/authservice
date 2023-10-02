package com.authservice.service.impl;

import com.authservice.dto.UserDto;
import com.authservice.dto.request.UserRequest;
import com.authservice.entity.User;
import com.authservice.repository.UserRepository;
import com.authservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        findByEmail(users.getEmail());
        return userDto;
    }

    private void findByEmail(String email) {
    }

    @Override
    public UserDto findByEmail(UserRequest email) {
        return null;
    }

    private ResponseEntity<?> RegisteredEmail(String email){
        if (email == null){
            return new ResponseEntity<>("User does not have an email", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(email,HttpStatus.OK);
    }
}
