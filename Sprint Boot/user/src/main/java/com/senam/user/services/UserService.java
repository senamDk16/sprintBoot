package com.senam.user.services;

import com.senam.user.DTO.UserDTO;
import com.senam.user.entity.User;
import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDTO CreateUser(User user);
    UserDTO getUserByUuid(UUID uuid);
    UserDTO updateUser(UUID uuid, UserDTO userDTO);
    List<UserDTO> getAllUsers();
    void deleteUser(UUID uuid);
}
