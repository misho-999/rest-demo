package com.example.rest;

import com.example.rest.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class MockMvsTest {
    //With SpringBootTest.WebEnvironment.MOCK We are not interested in that,
    // instead, we'll be configuring what we call MockMvc
    // And that is why we have a second annotation called @AutoConfigureMockMvc

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void testGetUserById() throws Exception {
        mockMvc
                .perform(get("/users/{id}", "9").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        mockMvc
                .perform(get("/users/all").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})           //Need spring-security-test dependency!!!
    public void testGetAllCars() throws Exception {

        mockMvc
                .perform(get("/cars/all").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})           //Need spring-security-test dependency!!!
    public void testPostUser() throws Exception {

        User user = new User();
        user.setUsername("Pencho");
        user.setEmail("pencho@gmail.com");
        user.setCars(new HashSet<>());

        mockMvc
                .perform(
                        post("/users/create")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(user)))
                .andExpect(status().isCreated());
    }
}
