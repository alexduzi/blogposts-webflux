package com.alexduzi.blogpostswebflux.repositories;

import com.alexduzi.blogpostswebflux.models.entities.Post;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends ReactiveMongoRepository<Post, String> {
}
