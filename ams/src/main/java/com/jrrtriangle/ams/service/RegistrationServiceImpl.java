package com.jrrtriangle.ams.service;

import com.jrrtriangle.ams.dto.UserDTO;
import com.jrrtriangle.ams.entity.User;
import com.jrrtriangle.ams.repository.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService {



    @Autowired
    UserRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public boolean createUser(UserDTO userDTO) throws Exception{
        try {

                repository.save(convertToEntity(userDTO));
                return true;

        }
        catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }

    @Override
    public boolean checkUserCreds(UserDTO userDTO) throws Exception {
        try {
            Optional<User> users = repository.findByEmail(userDTO.getEmail());

            return users.filter(user -> userDTO.getPassword().equals(user.getPassword())).isPresent();
        }
        catch (Exception e) {
            throw new Exception();
        }
    }

    @Override
    @Transactional
    public UserDTO getUserbyEmail(String email) throws Exception {
        try {

            Optional<User> user = repository.findByEmail(email);
            if (user.isPresent()) {

                return convertToDto(user.get());
            }
        }
        catch (Exception e) {
            throw new Exception();
        }
        return null;
    }

    private boolean checkExist(String email) {
        try {
            Optional<User> users = repository.findByEmail(email);
            if (users.isPresent()) {
                return true;
            }
        }
        catch (Exception e) {
        }
        return false;
    }

    private User convertToEntity(UserDTO userDto) throws ParseException {

        return modelMapper.map(userDto, User.class);
    }

    private UserDTO convertToDto(User user) throws ParseException {

        return modelMapper.map(user, UserDTO.class);
    }
}
