package com.luciano.springboot.app.crud.security.controllers;

import com.luciano.springboot.app.crud.security.entities.User;
import com.luciano.springboot.app.crud.security.exceptions.UserConflictException;
import com.luciano.springboot.app.crud.security.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) throws UserConflictException {
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) throws UserConflictException {
        user.setAdmin(false);
        return create(user);
    }
}
