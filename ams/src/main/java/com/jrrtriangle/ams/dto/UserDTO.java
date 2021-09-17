package com.jrrtriangle.ams.dto;

import com.jrrtriangle.ams.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Long userId;

    private String firstname;

    private String lastname;

    private  String email;

    private String password;

    private Set<Role> roles;


}
