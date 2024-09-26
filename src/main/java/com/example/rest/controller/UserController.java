package com.example.rest.controller;

import com.example.rest.model.User;
import com.example.rest.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    private ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity
                .ok()
                .body(userService.findAllUsers());
    }

    /**
     * Example http://localhost:8080/users/all-as-page?page=0&size=5&sort=id,desc
     * Query parameters:
     * page=0 (Starts from 0)
     * size=5
     * sort=id,desc
     * @return List of User in Pageable variant!!!
     */
    @GetMapping("/all-as-page")
    private ResponseEntity<List<User>> getAllUsersAsPage(Pageable pageable) {
        return ResponseEntity
                .ok()
                .body(userService.findAllUsersAsPage(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer id) {
        User user = userService.findUserById(id);

        if (user != null) {
            return ResponseEntity
                    .ok()
                    .body(user);
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    @PostMapping("/create")
    private ResponseEntity<Void> createUser(@RequestBody User user) {
        User savedUser = userService.createNewUser(user);

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
        User createdUser = userService.updateExistingUser(id, user);

        if (createdUser == null) {
            return ResponseEntity
                    .notFound()
                    .build();
        }

        return ResponseEntity
                .noContent()
                .build();
    }

    //Partial update. Only the email
    //http://localhost:8080/users/4?email=newemail@abv.bg
    @PatchMapping("/{id}")
    private ResponseEntity<Void> updateUserEmail(@PathVariable Integer id, @RequestParam String email) {

        User user = userService.updateUserEmail(id, email);

        if (user == null) {
            return ResponseEntity
                    .notFound()
                    .build();
        }

        user.setEmail(email);

        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteUserById(@PathVariable Integer id) {
        userService.deleteExistingUserById(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
