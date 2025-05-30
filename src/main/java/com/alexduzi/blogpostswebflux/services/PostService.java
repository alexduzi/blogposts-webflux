package com.alexduzi.blogpostswebflux.services;

import com.alexduzi.blogpostswebflux.exceptions.ResourceNotFoundException;
import com.alexduzi.blogpostswebflux.models.dto.PostDTO;
import com.alexduzi.blogpostswebflux.repositories.PostRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

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

    public Flux<PostDTO> fullSearch(String text, Instant minDate, Instant maxDate) {
        maxDate = maxDate.plusSeconds(86400);
        return postRepository.fullSearch(text, minDate, maxDate).map(PostDTO::new);
    }

    public Flux<PostDTO> findByUser(String id) {
        return postRepository.findByUser(new ObjectId(id)).map(PostDTO::new);
    }
}
