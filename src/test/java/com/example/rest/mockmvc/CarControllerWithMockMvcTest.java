package com.example.rest.mockmvc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class CarControllerWithMockMvcTest {

    @Autowired
    MockMvc mockMvc;  //Define a MockMVC environment

    @Test
    public void testBasicGet() throws Exception {
        this.mockMvc.perform(get("/users/all")
                        .accept("application/json")
                )
                .andExpect(status().isOk());       //Perform tests on mockMvc instance
    }

}
