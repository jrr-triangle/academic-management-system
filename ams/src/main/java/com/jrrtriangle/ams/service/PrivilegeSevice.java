package com.jrrtriangle.ams.service;

import com.jrrtriangle.ams.entity.Privilege;

public interface PrivilegeSevice {
    Privilege createPrivilege(Privilege privilege);
    Privilege findPrivilegByName(String name);
}
