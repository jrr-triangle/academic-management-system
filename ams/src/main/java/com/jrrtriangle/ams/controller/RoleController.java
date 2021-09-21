package com.jrrtriangle.ams.controller;

import com.jrrtriangle.ams.dto.ResponseDTO;
import com.jrrtriangle.ams.dto.RoleDTO;
import com.jrrtriangle.ams.entity.Endpoint;
import com.jrrtriangle.ams.entity.Role;
import com.jrrtriangle.ams.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping(path="/addupdaterole",consumes = "application/json", produces = "application/json")
    public ResponseDTO addUpdateRole(@RequestBody RoleDTO model){
        ResponseDTO responseDTO = new ResponseDTO();
        Role role = roleService.addUpdateRole(model);
        responseDTO.setData(role);
        responseDTO.setSuccess(true);
        responseDTO.setMessage("updated data successfully");

        return responseDTO;
    }

    }
