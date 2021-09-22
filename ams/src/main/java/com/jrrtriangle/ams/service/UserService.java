package com.jrrtriangle.ams.service;

import com.jrrtriangle.ams.dto.UserDto;
import com.jrrtriangle.ams.entity.UserEntity;
import com.jrrtriangle.ams.exception.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {
    public UserDto createUser(UserEntity user);
    UserEntity findUserById(Long id) throws UserNotFoundException;
}
