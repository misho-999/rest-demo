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
                .path("/" + user.getId())
                .buildAndExpand(user.getId())
                .toUri();

        userRepository.save(user);

        return ResponseEntity
                .created(location)
                .build();
    }

    //Update the whole User object
    @PutMapping("/{id}")
    private ResponseEntity<Void> updateUser(@PathVariable Integer id, @RequestBody User user) {
        User currUser = userRepository.findById(id).get();
        currUser.setCars(user.getCars());
        currUser.setEmail(user.getEmail());
        currUser.setUsername(user.getUsername());

        userRepository.save(currUser);

        return ResponseEntity
                .noContent()
                .build();
    }

    //Partial update. Only the email
    //http://localhost:8080/users/4?email=newemail@abv.bg
    @PatchMapping("/{id}")
    private ResponseEntity<Void> updateUserEmail(@PathVariable Integer id, @RequestParam String email) {
        User currUser = userRepository.findById(id).get();
        currUser.setEmail(email);

        userRepository.save(currUser);

        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        User currUser = userRepository.findById(id).get();

        userRepository.delete(currUser);

        return ResponseEntity
                .noContent()
                .build();
    }

}
