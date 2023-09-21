package com.authservice.controller;

import com.authservice.dto.UserDto;
import com.authservice.dto.request.UserRequest;
import com.authservice.entity.User;
import com.authservice.service.UserService;
import com.authservice.util.AppConstant;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    UserService userService;


    @GetMapping("/error")
    public String returnError(){
        return "Hello this is an error";
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserRequest user){
        UserDto userdto = userService.create(user);
        if(userdto == null){
            return new ResponseEntity<>("User not created, try again later", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userdto,HttpStatus.ACCEPTED);
    }


}
