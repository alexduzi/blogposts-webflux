package com.alexduzi.blogpostswebflux.repositories;

import com.alexduzi.blogpostswebflux.models.entities.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
}
