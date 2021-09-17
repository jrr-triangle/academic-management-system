package com.jrrtriangle.ams.service;

import com.jrrtriangle.ams.dto.UserDTO;

public interface RegistrationService {

    public boolean createUser(UserDTO userDTO) throws Exception;

    public boolean checkUserCreds(UserDTO userDTO) throws Exception;

    public UserDTO getUserbyEmail(String email) throws Exception;


}
