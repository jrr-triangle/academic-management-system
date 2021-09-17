package com.jrrtriangle.ams.security;

import com.jrrtriangle.ams.dto.UserDTO;
import com.jrrtriangle.ams.entity.Role;
import com.jrrtriangle.ams.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    RegistrationService regService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            UserDTO userdto = regService.getUserbyEmail(s);
            Collection<GrantedAuthority> d = new ArrayList<>();
            for(Role role : userdto.getRoles()){
                System.out.println(role.getRolename());
                d.add(new GrantedAuthority() {
                    @Override
                    public String getAuthority() {
                        return role.getRolename();
                    }
                });
            }
            System.out.println(d);
            return new User(userdto.getEmail(), userdto.getPassword(),
                    d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}