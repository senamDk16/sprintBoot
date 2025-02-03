package com.senam.user.services;

import com.senam.user.DTO.UserDTO;
import com.senam.user.entity.User;
import com.senam.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public UserDTO CreateUser(User user) {
        return UserMapper.toDTO(user);
    }

    @Override
    public UserDTO getUserByUuid(UUID uuid) {
        User user = userRepository.findByUuid(uuid);
        if(user == null){
            throw new EntityNotFoundException("User not found");
        }
        return UserMapper.toDTO(user);
    }

    @Override
    public UserDTO updateUser(UUID uuid, UserDTO userDTO) {
        User user = userRepository.findByUuid(uuid);
        if(user == null){
            throw new EntityNotFoundException("User not found");
        }
        user.setUsername(userDTO.username());
        user.setEmail(userDTO.email());
        userRepository.save(user);
        return UserMapper.toDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(UserMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(UUID uuid) {
        User user = userRepository.findByUuid(uuid);
        if(user == null){
            throw new EntityNotFoundException("User not found");
        }
        userRepository.deleteById(user.getId());
    }
}
