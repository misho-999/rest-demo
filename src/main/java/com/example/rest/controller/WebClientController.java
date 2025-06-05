package com.example.rest.controller;


import com.example.rest.model.Post;
import com.example.rest.service.WebClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("webclient")
@AllArgsConstructor
public class WebClientController {
    public static final Logger LOGGER = Logger.getLogger(WebClientController.class.getName());
    private final WebClientService webClientService;

    @GetMapping ("posts")
    ResponseEntity<List<Post>> getPosts(){
        LOGGER.info("!!!! WebClientService getPosts() is called!!!!!!!!!!!!!!!!");

        return ResponseEntity
                .ok()
                .body(webClientService.getAllPosts());
    }
}

