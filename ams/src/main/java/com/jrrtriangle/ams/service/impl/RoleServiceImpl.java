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
        Optional<Privilege> pr =privilegeRepository.findById(2l);
        Privilege privilege = new Privilege();

        if(privilegeSevice.findPrivilegByName("tester")!=null){
            privilege = privilegeSevice.findPrivilegByName("tester");
        }

        else  if(!pr.isPresent()){
            privilege =Privilege.builder()
                    .name("tester")
                    .endpoint("/test")
                    .build();
        }
        else {
            privilege = pr.get();
        }
        Set<Privilege> privileges = new HashSet<>();
        privileges.add(privilege);
        role.setPrivileges(privileges);
        return roleRepository.save(role);
    }

    @Override
    public Role findRoleByRole(String role) {
        return roleRepository.findByRole(role);
    }
}
