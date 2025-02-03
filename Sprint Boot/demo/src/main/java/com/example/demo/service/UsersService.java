package com.example.demo.service;

import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.Users;

import java.util.List;
public interface UsersService {
    UserDTO createUser(Users users);
    List<UserDTO> readUser();
    UserDTO readOneUser(Long id);
}
