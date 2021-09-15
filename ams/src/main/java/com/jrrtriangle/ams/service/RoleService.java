package com.jrrtriangle.ams.service;

import com.jrrtriangle.ams.entity.Role;

public interface RoleService {
    Role addRole(Role role);
    public Role findRoleByRole(String role);
}
