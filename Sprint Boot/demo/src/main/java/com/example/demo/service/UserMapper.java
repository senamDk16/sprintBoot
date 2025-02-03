package com.example.demo.service;

import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.Users;
import org.springframework.stereotype.Service;


@Service
public class UserMapper {

    //User -> UserDTO
    public static UserDTO toDTO (Users user){
        if(user == null){
            return null;
        }

        return new UserDTO(user.getUsername(), user.getEmail(), user.getContact());
    }

    public static Users toUser(UserDTO userDTO){
        if(userDTO == null){
            return null;
        }
        Users user = new Users();
        user.setUsername(userDTO.username());
        user.setContact(userDTO.contact());
        user.setEmail(userDTO.email());
        return  user;
    }
}
