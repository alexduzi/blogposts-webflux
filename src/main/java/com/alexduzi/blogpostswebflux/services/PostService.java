package com.alexduzi.blogpostswebflux.services;

import com.alexduzi.blogpostswebflux.exceptions.ResourceNotFoundException;
import com.alexduzi.blogpostswebflux.models.dto.PostDTO;
import com.alexduzi.blogpostswebflux.repositories.PostRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Flux<PostDTO> findAll() {
        return postRepository.findAll().map(PostDTO::new);
    }

    public Mono<PostDTO> findById(String id) {
        return postRepository.findById(id)
                .map(PostDTO::new)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Post not found!")));
    }

    public Flux<PostDTO> findByTitle(String text) {
        return postRepository.searchTitle(text)
                .map(PostDTO::new);
    }
}
