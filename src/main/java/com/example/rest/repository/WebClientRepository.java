package com.example.rest.repository;

import com.example.rest.model.Post;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public class WebClientRepository {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    public List<Post> findAllPosts(){
        WebClient client = WebClient.create(BASE_URL);

        Mono<List<Post>> postsMono = client.get()
                .uri("/posts")
                .retrieve()
                .bodyToFlux(Post.class)
                .collectList();

        List<Post> posts = postsMono.block();

        return posts;
    }
}
