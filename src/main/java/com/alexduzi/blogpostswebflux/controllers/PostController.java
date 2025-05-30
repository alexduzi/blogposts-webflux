package com.alexduzi.blogpostswebflux.controllers;

import com.alexduzi.blogpostswebflux.models.dto.PostDTO;
import com.alexduzi.blogpostswebflux.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public Flux<PostDTO> findAll() {
        return postService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Mono<ResponseEntity<PostDTO>> findById(@PathVariable String id) {
        return postService.findById(id).map(ResponseEntity::ok);
    }
}
