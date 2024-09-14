package com.luciano.springboot.app.crud.security.services;

import com.luciano.springboot.app.crud.security.entities.User;
import com.luciano.springboot.app.crud.security.exceptions.UserConflictException;

import java.util.List;

public interface IUserService {
    List<User> findAll();

    User save(User user) throws UserConflictException;
}
