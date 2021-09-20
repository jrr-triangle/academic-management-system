package com.jrrtriangle.ams.controller;

import com.jrrtriangle.ams.dto.UserDto;
import com.jrrtriangle.ams.entity.Role;
import com.jrrtriangle.ams.entity.UserEntity;
import com.jrrtriangle.ams.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

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

    @PostMapping
    @RequestMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserEntity user,@PathVariable Long id){
        UserEntity findUser = userService.findUserById(id);
        if(user.getFirstname()!=null && !user.getFirstname().isEmpty()){
            findUser.setFirstname(user.getFirstname());
        }
        if(user.getLastname()!=null && !user.getLastname().isEmpty()){
            findUser.setLastname(user.getLastname());
        }
        if(user.getRoles().toArray().length>0){
            Set<Role> roles = findUser.getRoles();
            user.getRoles().forEach(role -> {
                if(!roles.contains(role)){
                    roles.add(role);
                }
            });
            findUser.setRoles(roles);
        }
        return new ResponseEntity<>(userService.createUser(findUser), HttpStatus.OK);
    }

    @PostMapping
    @RequestMapping("/role/update/{id}")
    public ResponseEntity<UserDto> updateUserRole(@RequestBody UserEntity user,@PathVariable Long id){
        UserEntity findUser = userService.findUserById(id);
        if(user.getFirstname()!=null && !user.getFirstname().isEmpty()){
            findUser.setFirstname(user.getFirstname());
        }
        if(user.getLastname()!=null && !user.getLastname().isEmpty()){
            findUser.setLastname(user.getLastname());
        }
        if(user.getRoles().toArray().length>0){
            Set<Role> roles = findUser.getRoles();
            user.getRoles().forEach(role -> {
                if(!roles.contains(role)){
                    roles.add(role);
                }
            });
            findUser.setRoles(roles);
        }
        return new ResponseEntity<>(userService.createUser(findUser), HttpStatus.OK);
    }

}
