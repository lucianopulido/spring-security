package com.luciano.springboot.app.crud.security.services;

import com.luciano.springboot.app.crud.security.entities.Role;
import com.luciano.springboot.app.crud.security.entities.User;
import com.luciano.springboot.app.crud.security.exceptions.UserConflictException;
import com.luciano.springboot.app.crud.security.repositories.RoleRepository;
import com.luciano.springboot.app.crud.security.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) this.userRepository.findAll();
    }

    @Override
    @Transactional
    public User save(User user) throws UserConflictException {

        boolean hasUser = this.userRepository.existsByUsername(user.getUsername());

        if (hasUser) {
            throw new UserConflictException("User already exists");
        }

        Optional<Role> optionalRole = roleRepository.findByName("USER");
        List<Role> roles = new ArrayList<>();
        optionalRole.ifPresent(roles::add);

        if (user.isAdmin()) {
            Optional<Role> optionalRoleAdmin = roleRepository.findByName("ADMIN");
            optionalRoleAdmin.ifPresent(roles::add);
        }

        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }
}
