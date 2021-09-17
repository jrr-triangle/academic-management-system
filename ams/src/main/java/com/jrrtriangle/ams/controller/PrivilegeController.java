package com.jrrtriangle.ams.controller;

import com.jrrtriangle.ams.entity.Privilege;
import com.jrrtriangle.ams.service.PrivilegeSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("privilege")
public class PrivilegeController {
    @Autowired
    private PrivilegeSevice privilegeSevice;

    @PostMapping
    public ResponseEntity<Privilege> createPrivilege(@RequestBody Privilege privilege){
        return new ResponseEntity<Privilege>(privilegeSevice.createPrivilege(privilege), HttpStatus.CREATED);
    }
}
