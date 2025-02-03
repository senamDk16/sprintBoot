package com.senam.user.controller;

import com.senam.user.DTO.UserDTO;
import com.senam.user.entity.User;
import com.senam.user.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController (UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getAllusers(){

        return userService.getAllUsers();
    }
    @GetMapping("/{uuid}")
    public UserDTO getUserById(@PathVariable UUID uuid){
        return userService.getUserByUuid(uuid);
    }

    @PostMapping
    public UserDTO createUser(@RequestBody User user){
        System.out.println(user);
        return userService.CreateUser(user);
    }

//    @PostMapping
//    public ResponseEntity<User> createUser(@RequestBody User user){
//        User newUser = userService.CreateUser(user);
//
//        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
//    }


    @PutMapping("/{id}")
    public  UserDTO updateUser(@PathVariable UUID uuid, @RequestBody UserDTO userDetail){
        return  userService.updateUser(uuid, userDetail);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID uuid){
        userService.deleteUser(uuid);
    }
}
