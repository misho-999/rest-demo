package com.example.rest.service.impl;

import com.example.rest.model.Post;
import com.example.rest.repository.WebClientRepository;
import com.example.rest.service.WebClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WebClientServiceImpl implements WebClientService {

    private final WebClientRepository webClientRepository;

    @Override
    public List<Post> getAllPosts() {
        return webClientRepository.findAllPosts();
    }
}
