package com.jrrtriangle.ams.service.impl;

import com.jrrtriangle.ams.entity.Role;
import com.jrrtriangle.ams.repository.RoleRepository;
import com.jrrtriangle.ams.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findRoleByRole(String role) {
        return roleRepository.findByRole(role);
    }
}
