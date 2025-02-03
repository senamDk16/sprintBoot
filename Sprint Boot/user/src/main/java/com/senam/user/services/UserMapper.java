package com.senam.user.services;

import com.senam.user.DTO.UserDTO;
import com.senam.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    //user -> DTO
    public static UserDTO toDTO(User user){
        if(user == null){
            return null;
        }

        return new UserDTO(user.getUuid(), user.getUsername(), user.getEmail());
    }

    public static User toEntity(UserDTO userDTO){
        if(userDTO == null){
            return null;
        }
        User user = new User();
        user.setUsername(userDTO.username());
        user.setEmail(userDTO.email());
        user.setUuid(userDTO.uuid());
        return user;
    }
}
