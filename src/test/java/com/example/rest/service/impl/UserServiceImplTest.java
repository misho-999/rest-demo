package com.example.rest.service.impl;

import com.example.rest.model.User;
import com.example.rest.repository.UserRepository;
import lombok.experimental.PackagePrivate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private final List<User> mockUsers = getMockUsetList();

    @Test
    void findAllUsers() {
        //Arrange

        when(userRepository.findAll()).thenReturn(mockUsers);

        //Act
        List<User> allUsers = userService.findAllUsers();

        //Assert
        assertEquals(1, allUsers.size());
        assertEquals(allUsers.getFirst().getEmail(), "user1@avb");
    }

    @Test
    void findAllUsersAsPage() {
    }

    @Test
    void findUserById() {
    }

    @Test
    void createNewUser() {
    }

    @Test
    void updateExistingUser() {
    }

    @Test
    void updateUserEmail() {
    }

    @Test
    void deleteExistingUserById() {
    }

    private List<User> getMockUsetList(){
        User user1 = new User();
        user1.setId(1);
        user1.setCars(new HashSet<>());
        user1.setEmail("user1@avb");
        user1.setUsername("User 1");
        return List.of(user1);
    }
}