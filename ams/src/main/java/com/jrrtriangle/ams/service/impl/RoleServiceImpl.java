package com.jrrtriangle.ams.service.impl;

import com.jrrtriangle.ams.entity.Privilege;
import com.jrrtriangle.ams.entity.Role;
import com.jrrtriangle.ams.repository.PrivilegeRepository;
import com.jrrtriangle.ams.repository.RoleRepository;
import com.jrrtriangle.ams.service.PrivilegeSevice;
import com.jrrtriangle.ams.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PrivilegeRepository privilegeRepository;
    @Autowired
    private PrivilegeSevice privilegeSevice;
    @Override
    public Role addRole(Role role) {

        Set<Privilege> privileges = new HashSet<>();
        role.getPrivileges().forEach(privilege -> {
            Optional<Privilege> pr =privilegeRepository.findById(privilege.getId());
            Privilege findPrivilege = new Privilege();

            if(pr.isPresent()){
                findPrivilege = pr.get();
            }

            else  {
                findPrivilege =Privilege.builder()
                        .name("tester")
                        .endpoint("/test")
                        .build();
            }
            privileges.add(findPrivilege);
        });


        role.setPrivileges(privileges);
        return roleRepository.save(role);
    }

    @Override
    public Role findRoleByRole(String role) {
        return roleRepository.findByRole(role);
    }

    @Override
    public Role findByRoleId(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        if(role.isPresent()){
            return role.get();
        }else{
            return null;
        }
    }
}
