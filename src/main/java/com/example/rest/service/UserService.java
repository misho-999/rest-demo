package com.example.rest.service;

import com.example.rest.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();

    List<User> findAllUsersAsPage(Pageable pageable);

    User findUserById(Integer id);

    User createNewUser(User user);

    User updateExistingUser(Integer id, User user);

    void deleteExistingUserById(Integer id);

    User updateUserEmail(Integer id, String email);
}
