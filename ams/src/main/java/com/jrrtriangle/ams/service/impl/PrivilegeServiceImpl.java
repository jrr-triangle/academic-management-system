package com.jrrtriangle.ams.service.impl;

import com.jrrtriangle.ams.entity.Privilege;
import com.jrrtriangle.ams.entity.Role;
import com.jrrtriangle.ams.repository.PrivilegeRepository;
import com.jrrtriangle.ams.service.PrivilegeSevice;
import com.jrrtriangle.ams.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class PrivilegeServiceImpl implements PrivilegeSevice {
    @Autowired
    private PrivilegeRepository privilegeRepository;
    @Autowired
    private RoleService roleService;
    @Override
    public Privilege createPrivilege(Privilege privilege) {
        Privilege privilege1 = privilegeRepository.findByName(privilege.getName());
        if(privilege1!=null){
            throw new RuntimeException("Privilege already exist");
        }
        Privilege newPrivilege = privilegeRepository.save(privilege);
        Role role = roleService.findByRoleId(1L);
        Set<Privilege> privileges =  role.getPrivileges();
        privileges.add(newPrivilege);
        role.setPrivileges(privileges);
        roleService.addRole(role);
        return newPrivilege;
    }

    @Override
    public Privilege findPrivilegByName(String name) {
        return privilegeRepository.findByName(name);
    }

    @Override
    public Privilege findById(Long id) {
       Optional<Privilege> privilege= privilegeRepository.findById(id);
       if(privilege.isPresent()){
           return privilege.get();
       }else {
           return null;
       }

    }
}
