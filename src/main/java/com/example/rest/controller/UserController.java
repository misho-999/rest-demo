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

        User user = userRepository.findById(id).orElse(null);

        if (user != null) {
            return ResponseEntity
                    .ok()
                    .body(userRepository.findById(id).get());
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }


    }

    @PostMapping("/create")
    private ResponseEntity<Void> createUser(@RequestBody User user) {
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/" + savedUser.getId())
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .build();
    }

    //Update the whole User object
    @PutMapping("/{id}")
    private ResponseEntity<Void> updateUser(@PathVariable Integer id, @RequestBody User user) {
        User currUser = userRepository.findById(id).orElse(null);

        if (currUser == null) {
            return ResponseEntity
                    .notFound()
                    .build();
        }

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
        User currUser = userRepository.findById(id).orElse(null);

        if (currUser == null) {
            return ResponseEntity
                    .notFound()
                    .build();
        }

        currUser.setEmail(email);

        userRepository.save(currUser);

        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        User currUser = userRepository.findById(id).orElse(null);

        if (currUser == null) {
            return ResponseEntity
                    .notFound()
                    .build();
        }

        userRepository.delete(currUser);

        return ResponseEntity
                .noContent()
                .build();
    }
}
