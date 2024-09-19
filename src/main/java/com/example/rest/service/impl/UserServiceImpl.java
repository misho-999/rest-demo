package com.example.rest.service.impl;

import com.example.rest.model.User;
import com.example.rest.repository.UserRepository;
import com.example.rest.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User createNewUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateExistingUser(Integer id, User user) {

        User currUser = findUserById(id);

        if (currUser == null) {
            return null;
        }

        currUser.setCars(user.getCars());
        currUser.setEmail(user.getEmail());
        currUser.setUsername(user.getUsername());

        userRepository.save(currUser);

        return currUser;
    }

    @Override
    public User updateUserEmail(Integer id, String email) {
        User user = findUserById(id);

        user.setEmail(email);
        userRepository.save(user);

        return user;
    }

    @Override
    public void deleteExistingUserById(Integer id) {
        userRepository.delete(findUserById(id));
    }
}
