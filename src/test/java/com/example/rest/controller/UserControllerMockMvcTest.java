package com.example.rest.controller;


import com.example.rest.model.User;
import com.example.rest.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class UserControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private User sampleUser;

    @BeforeEach
    void setUp() {
        sampleUser = new User();
        sampleUser.setId(1);
        sampleUser.setUsername("Mihail Angelov");
        sampleUser.setEmail("misho@abv.bg");
        sampleUser.setCars(Set.of());
    }

    @Test
    @WithMockUser(username = "admin", roles = "USER")
    void testGetAllUsers() throws Exception {
        Mockito.when(userService.findAllUsers()).thenReturn(List.of(sampleUser));

        mockMvc.perform(get("/users/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("Mihail Angelov"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "USER")
    void testGetAllUsersAsPage() throws Exception {
        Mockito.when(userService.findAllUsersAsPage(any(Pageable.class))).thenReturn(List.of(sampleUser));

        mockMvc.perform(get("/users/all-as-page")
                        .param("page", "0")
                        .param("size", "5")
                        .param("sort", "id,desc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("Mihail Angelov"));
    }

    @Test
    void testGetUserById_Found() throws Exception {
        Mockito.when(userService.findUserById(1)).thenReturn(sampleUser);

        mockMvc.perform(get("/users/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("Mihail Angelov"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "USER")
    void testGetUserById_NotFound() throws Exception {
        Mockito.when(userService.findUserById(99)).thenReturn(null);

        mockMvc.perform(get("/users/{id}", "99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateUser() throws Exception {
        Mockito.when(userService.createNewUser(any(User.class))).thenReturn(sampleUser);

        mockMvc.perform(post("/users/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"username":"Mihail Angelov","email":"misho@abv.bg"}"""))
               .andExpect(status().isCreated());
    }

    @Test
    void testUpdateUser_Found() throws Exception {
        Mockito.when(userService.updateExistingUser(eq(1), any(User.class))).thenReturn(sampleUser);

        mockMvc.perform(put("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
               {"username":"Mihail Angelov","email":"misho@abv.bg"}"""))
               .andExpect(status().isNoContent());
    }

    @Test
    void testUpdateUser_NotFound() throws Exception {
        Mockito.when(userService.updateExistingUser(eq(99), any(User.class))).thenReturn(null);

        mockMvc.perform(put("/users/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
               {"username":"Mihail Angelov","email":"misho@abv.bg"}"""))
               .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateUserEmail_Found() throws Exception {
        Mockito.when(userService.updateUserEmail(1, "newemail@abv.bg")).thenReturn(sampleUser);

        mockMvc.perform(patch("/users/1")
                        .param("email", "newemail@abv.bg"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testUpdateUserEmail_NotFound() throws Exception {
        Mockito.when(userService.updateUserEmail(99, "newemail@abv.bg")).thenReturn(null);

        mockMvc.perform(patch("/users/99")
                        .param("email", "newemail@abv.bg"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteUserById() throws Exception {
        Mockito.doNothing().when(userService).deleteExistingUserById(1);

        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isNoContent());
    }
}
