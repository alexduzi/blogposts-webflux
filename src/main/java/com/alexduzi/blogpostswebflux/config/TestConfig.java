package com.alexduzi.blogpostswebflux.config;

import com.alexduzi.blogpostswebflux.models.entities.Post;
import com.alexduzi.blogpostswebflux.models.entities.User;
import com.alexduzi.blogpostswebflux.repositories.PostRepository;
import com.alexduzi.blogpostswebflux.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig {

    private final UserRepository userRepository;

    private final PostRepository postRepository;

    public TestConfig(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @PostConstruct
    public void init() {
        Mono<Void> deleteUsers = userRepository.deleteAll();
        deleteUsers.subscribe();

        Mono<Void> deletePosts = postRepository.deleteAll();
        deletePosts.subscribe();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        Flux<User> saveAllUsers = userRepository.saveAll(Arrays.asList(maria, alex, bob));
        saveAllUsers.subscribe();

        Post post1 = new Post(null, Instant.parse("2022-11-21T18:35:24.00Z"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", maria.getId(), maria.getName());
        Post post2 = new Post(null, Instant.parse("2022-11-23T17:30:24.00Z"), "Bom dia", "Acordei feliz hoje!", maria.getId(), maria.getName());

        post1.addComment("Boa viagem mano!", Instant.parse("2022-11-21T18:52:24.00Z"), alex.getId(), alex.getName());
        post1.addComment("Aproveite!", Instant.parse("2022-11-22T11:35:24.00Z"), bob.getId(), bob.getName());

        post2.addComment("Tenha um ótimo dia!", Instant.parse("2022-11-23T18:35:24.00Z"), alex.getId(), alex.getName());

        Flux<Post> saveAllPosts = postRepository.saveAll(Arrays.asList(post1, post2));
        saveAllPosts.subscribe();

//        maria.getPosts().addAll(Arrays.asList(post1, post2));
//        Mono<User> saveMaria = userRepository.save(maria);
//        saveMaria.subscribe();
    }
}
