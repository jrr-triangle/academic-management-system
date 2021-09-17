package com.jrrtriangle.ams.service.impl;

import com.jrrtriangle.ams.entity.Privilege;
import com.jrrtriangle.ams.repository.PrivilegeRepository;
import com.jrrtriangle.ams.service.PrivilegeSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivilegeServiceImpl implements PrivilegeSevice {
    @Autowired
    private PrivilegeRepository privilegeRepository;
    @Override
    public Privilege createPrivilege(Privilege privilege) {
        return privilegeRepository.save(privilege);
    }

    @Override
    public Privilege findPrivilegByName(String name) {
        return privilegeRepository.findByName(name);
    }
}
