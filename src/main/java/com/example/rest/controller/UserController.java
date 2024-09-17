package com.example.rest.controller;

import com.example.rest.model.User;
import com.example.rest.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/all")
    private ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity
                .ok()
                .body(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer id) {
        return ResponseEntity
                .ok()
                .body(userRepository.findById(id).get());
    }

    @PostMapping("/create")
    private ResponseEntity<Void> createUser(@RequestBody User user) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/users")
                .buildAndExpand(user.getId())
                .toUri();

        userRepository.save(user);

        return ResponseEntity
                .created(location)
                .build();
    }
}
