package com.jrrtriangle.ams.service.impl;

import com.jrrtriangle.ams.dto.UserDto;
import com.jrrtriangle.ams.entity.Role;
import com.jrrtriangle.ams.entity.UserEntity;
import com.jrrtriangle.ams.repository.RoleRepository;
import com.jrrtriangle.ams.repository.UserRepository;
import com.jrrtriangle.ams.service.RoleService;
import com.jrrtriangle.ams.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            UserEntity userEntity = userRepository.findByEmail(email);
            if(userEntity==null){
                throw new UsernameNotFoundException(email);
            }
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            userEntity.getRoles().forEach(role->authorities.add(new SimpleGrantedAuthority(role.getRole())));
            return new User(userEntity.getEmail(),userEntity.getPassword(), authorities);
       // return new User("raihan","password",  new ArrayList<>());

    }

    @Override
    public UserDto createUser(UserEntity user) {
        if(userRepository.findByEmail(user.getEmail())!=null) throw new RuntimeException("User already exists");
       Role findRole = roleService.findRoleByRole("USER");
       if(findRole==null){
           Role newRole = Role.builder()
                   .role("USER")
                   .build();
           findRole = roleService.addRole(newRole);
       }
        Set<Role> roles = new HashSet<>();
        roles.add(findRole);
        user.setRoles(roles);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        UserEntity userEntity = userRepository.save(user);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userEntity,userDto);
        return userDto;
    }
}
