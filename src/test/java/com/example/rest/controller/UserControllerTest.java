package com.example.rest.controller;

import com.example.rest.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {
    // TestRestTemplate is not RestTemplate.
    // That's something really useful because you don't want to make any mistakes and inject the TestRestTemplate in your Components.
    // So it's not of type RestTemplate but it behaves like RestTemplate but it has some advantages in a test environment.
    // For instance, it accepts a relative path instead of an absolute path.
    // The RestTemplate requires an absolute path.
    // That means that you have to define the entire path like "localhost:8080/......"
    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    public void addAndDeleteUser() throws URISyntaxException {
        String addUrl = "/users/create";     //!!! Relative path !!!
        User forObject = testRestTemplate.getForObject("/users/4", User.class);

        String rootUri = testRestTemplate.getRootUri();

        User user = new User();
        user.setUsername("Asen");
        user.setEmail("SSSS@ddd");

        URI userLocation = testRestTemplate.postForLocation(addUrl, user);
        URI newUri = new URI(userLocation.toString().replace(rootUri, "").replace("/create", ""));

        User newUser = testRestTemplate.getForObject(newUri, User.class);

        assertEquals(newUser.getUsername(), "Asen");

        testRestTemplate.delete(newUri);
        ResponseEntity<User> response = testRestTemplate.getForEntity(newUri, User.class);

        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);   //404
        System.out.println();
    }
}