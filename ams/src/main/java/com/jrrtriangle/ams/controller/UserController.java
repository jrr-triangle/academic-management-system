package com.jrrtriangle.ams.controller;

import com.jrrtriangle.ams.dto.UserDto;
import com.jrrtriangle.ams.entity.UserEntity;
import com.jrrtriangle.ams.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public String getUser(){
        return "Welcome Raihan";
    }

    @PostMapping
    @RequestMapping("/register")
    public ResponseEntity<UserDto> createUser(@RequestBody UserEntity user){
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

}
