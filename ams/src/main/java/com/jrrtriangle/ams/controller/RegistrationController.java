package com.jrrtriangle.ams.controller;


import com.jrrtriangle.ams.dto.ResponseDTO;
import com.jrrtriangle.ams.dto.UserDTO;
import com.jrrtriangle.ams.service.RegistrationService;
import com.jrrtriangle.ams.security.JwtUtil;
import com.jrrtriangle.ams.security.MyUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/registration")
public class RegistrationController {



    @Autowired
    RegistrationService registrationService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    JwtUtil jwtUtil;


    @PostMapping(path="/newuser",consumes = "application/json", produces = "application/json")
    public ResponseDTO registration(@RequestBody UserDTO model) {
        ResponseDTO responseDTO = new ResponseDTO();

        System.out.println(model.getRoles()+" role");

        try {
            boolean status = registrationService.createUser(model);
            responseDTO.setSuccess(status);
            if(status)
                responseDTO.setMessage("user created successfully");
            else
                responseDTO.setMessage("user exists");
        }
        catch (Exception e) {
            System.out.println(e);
        }

        return responseDTO;
    }

    @PostMapping(path="/login",consumes = "application/json", produces = "application/json")
    public ResponseDTO login(@RequestBody UserDTO model) {
        ResponseDTO responseDTO = new ResponseDTO();
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    model.getEmail(),model.getPassword()
            ));
            final UserDetails userDetails = myUserDetailsService.loadUserByUsername(model.getEmail());
            String token = jwtUtil.generateToken(userDetails);

            responseDTO.setSuccess(true);
            responseDTO.setMessage("user login successful");
            responseDTO.setJwt(token);
        }
        catch (Exception e) {
            responseDTO.setMessage("user login failed");
            responseDTO.setSuccess(false);
            System.out.println(e);

        }

        return responseDTO;
    }

}
