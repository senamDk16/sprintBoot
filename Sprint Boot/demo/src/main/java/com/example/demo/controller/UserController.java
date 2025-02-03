package com.example.demo.controller;



import com.example.demo.DTO.ApiException;
import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.Users;
import com.example.demo.service.UsersService;
import org.hibernate.query.sqm.EntityTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/users")
@RestController
public class UserController {
    public UsersService usersService;

    public UserController(UsersService usersService){
        this.usersService = usersService;
    }
    @GetMapping
    public ResponseEntity getUser(){
        return new ResponseEntity<>(usersService.readUser(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOnlyUser(@PathVariable Long id){

        UserDTO userDTO = usersService.readOneUser(id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity postUser(@RequestBody Users user){
        return new ResponseEntity<>(usersService.createUser(user), HttpStatus.CREATED);
    }


}
