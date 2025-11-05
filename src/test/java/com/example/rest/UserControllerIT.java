package com.example.rest;

import com.example.rest.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.net.URI;
import java.util.HashSet;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testPostUser() throws Exception {

        User user = new User();
        user.setUsername("Misho");
        user.setEmail("pencho@gmail.com");
        user.setCars(new HashSet<>());

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer your-token-here");
        headers.setContentType(MediaType.APPLICATION_JSON);

        RequestEntity<User> request = RequestEntity.post(new URI("/users/create"))
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON)
                .body(user);

        ResponseEntity<Void> response = testRestTemplate.exchange(request, Void.class);
        String path = Objects.requireNonNull(response.getHeaders().getLocation()).getPath();

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(path);

        String userId = path.replace("/users/create/", "");

        RequestEntity<Void> deleteRequest = RequestEntity.delete(new URI("/users/" + userId))
                .headers(headers)
                .build();

        //Delete the record
        testRestTemplate.exchange(deleteRequest, Void.class);
    }
}