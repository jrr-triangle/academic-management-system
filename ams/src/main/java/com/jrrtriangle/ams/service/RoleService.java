package com.jrrtriangle.ams.service;

import com.jrrtriangle.ams.dto.RoleDTO;
import com.jrrtriangle.ams.entity.Endpoint;
import com.jrrtriangle.ams.entity.Role;
import com.jrrtriangle.ams.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    final RoleRepository roleRepository;

    final ModelMapper modelMapper;

    public RoleService(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    public Role addUpdateRole(RoleDTO roleDTO) {
        return roleRepository.save(modelMapper.map(roleDTO, Role.class));

    }
}
