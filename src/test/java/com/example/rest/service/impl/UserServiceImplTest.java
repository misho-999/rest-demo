package com.example.rest.service.impl;

import com.example.rest.model.User;
import com.example.rest.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    UserRepository userRepository;

    @Mock
    Page<User> page;

    /*You can not use @InjectMocks on just the interface alone, because Mockito needs to know what concrete class to instantiate.*/
    @InjectMocks
    private UserServiceImpl userService;

    private final List<User> mockUsers = getMockUsersList();
    private final User mockUser = getMockUser();


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
    void findAllUsersAsPageTest() {
        //Arrange
        PageRequest pageRequest = PageRequest.of(1, 2, Sort.by(Sort.Direction.DESC, "id"));

        when(userRepository.findAll(pageRequest)).thenReturn(page);
        when(page.getContent()).thenReturn(mockUsers);

        //Act
        List<User> allUsers = userService.findAllUsersAsPage(pageRequest);

        //Assert
        assertEquals(1, allUsers.size());
        assertEquals(allUsers.getFirst().getEmail(), "user1@avb");
    }

    @Test
    void findUserByIdTest() {
        //Arrange
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(mockUsers.getFirst()));

        //Act
        User userById = userService.findUserById(5);

        //Assert
        assertEquals(1, userById.getId());
    }

    @Test
    void createNewUserTest() {
        //Arrange
        when(userRepository.save(mockUser)).thenReturn(mockUser);

        //Act
        userService.createNewUser(mockUser);

        //Assert
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void updateExistingUserTest() {
        //Arrange
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(mockUser));
        when(userRepository.save(mockUser)).thenReturn(mockUser);

        //Act

        userService.updateExistingUser(anyInt(), mockUser);

        //Assert
        verify(userRepository, times(1)).save(any(User.class));

    }

    @Test
    void updateExistingUserWhenUserIsMissingTest() {
        //Arrange
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());

        //Act
        User user = userService.updateExistingUser(anyInt(), mockUser);

        //Assert
        assertNull(user);
    }

    @Test
    void updateUserEmailTest() {
        //Arrange
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(mockUser));
        when(userRepository.save(any(User.class))).thenReturn(mockUser);

        //Act
        userService.updateUserEmail(1, anyString());

        //Assert
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void deleteExistingUserByIdTest() {
        //Arrange


        //Act


        //Assert
    }

    private User getMockUser() {
        User user = new User();
        user.setUsername("Fake user");
        user.setEmail("fake@gmail.com");
        user.setId(1);

        return user;
    }

    private List<User> getMockUsersList() {
        User user1 = new User();
        user1.setId(1);
        user1.setCars(new HashSet<>());
        user1.setEmail("user1@avb");
        user1.setUsername("User 1");
        return List.of(user1);
    }
}