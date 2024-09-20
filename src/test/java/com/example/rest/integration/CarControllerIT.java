package com.example.rest.integration;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class CarControllerIT {
    //With SpringBootTest.WebEnvironment.MOCK We are not interested in that,
    // instead, we'll be configuring what we call MockMvc
    // And that is why we have a second annotation called @AutoConfigureMockMvc

    @Autowired
    private MockMvc mockMvc;

//    @Test
//    public void testGetUser() throws Exception {
//        mockMvc
//                .perform(get("/users/{id}", "3").accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("application/json"));
//    }
//
//    @Test
//    public void testGetAllCars() throws Exception {
//        mockMvc
//                .perform(get("/users/all").accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("application/json"));
//    }
}
