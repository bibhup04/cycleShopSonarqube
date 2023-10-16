package com.talentsprint.cycleshop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.talentsprint.cycleshop.entity.User;
import com.talentsprint.cycleshop.exception.CycleShopBusinessException;
import com.talentsprint.cycleshop.repository.UserRepository;

@Service
public class UserService {

    private BCryptPasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public UserService(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Optional<User> authenticate(String username, String password) {
        Optional<User> optUser = userRepository.findByName(username);
        if (optUser.isEmpty()) {
            throw new CycleShopBusinessException("User not found");
        }
        if (!optUser.get().getPassword().equals(password)) {
            return Optional.empty();
        }
        return optUser;
    }

    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // user.setPassword("{noop}" + user.getPassword());
        return userRepository.save(user);
    }

    public Optional<User> getById(long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getByName(String name) {
        return userRepository.findByName(name);
    }

    // public ResponseEntity<String> registerUser(User user) {
    // try {
    // // Validate user data (e.g., check for duplicate usernames)
    // Optional<User> existingUser = userRepository.findByName(user.getName());
    // if (existingUser.isPresent()) {
    // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already
    // exists");
    // }

    // // Check if the username and password are "admin"
    // if ("admin".equals(user.getName()) && "admin".equals(user.getPassword())) {
    // user.setRole("ADMIN");
    // } else {
    // user.setRole("USER");
    // }

    // // Hash the user's password
    // user.setPassword(passwordEncoder.encode(user.getPassword()));

    // // Save the user to the database
    // userRepository.save(user);

    // return ResponseEntity.ok("User registered successfully");
    // } catch (Exception e) {
    // // Handle registration errors, such as validation failures or database errors
    // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration
    // failed: " + e.getMessage());
    // }
    // }

    public boolean authenticateUser(String username, String password) {
        Optional<User> user = userRepository.findByName(username);

        return user.isPresent() && userMatchesPassword(user.get(), password);
    }

    private boolean userMatchesPassword(User user, String password) {
        return user.getPassword().equals(password);
    }

}
