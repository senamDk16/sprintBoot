package com.example.demo.service;


import com.example.demo.DTO.UserDTO;
import com.example.demo.Repository.UsersRepository;
import com.example.demo.entity.Users;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImp implements UsersService{
    private final UsersRepository usersRepository;
    public UserServiceImp (UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDTO createUser(Users user) {
        Users saveUser = usersRepository.save(user);
        return UserMapper.toDTO(saveUser);
    }

    @Override
    public List<UserDTO> readUser() {
        return usersRepository.findAll().stream().map(UserMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO readOneUser(Long id) {
            Optional<Users> user = usersRepository.findById(id);

            return UserMapper.toDTO(user.orElseThrow( ()-> new EntityNotFoundException("User not found") ));
    }
}
