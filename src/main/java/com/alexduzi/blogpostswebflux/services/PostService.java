package com.alexduzi.blogpostswebflux.services;

import com.alexduzi.blogpostswebflux.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
}
