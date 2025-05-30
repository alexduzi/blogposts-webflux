package com.alexduzi.blogpostswebflux.controllers;

import com.alexduzi.blogpostswebflux.models.dto.PostDTO;
import com.alexduzi.blogpostswebflux.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.time.Instant;

import static com.alexduzi.blogpostswebflux.controllers.util.URL.convertDate;
import static com.alexduzi.blogpostswebflux.controllers.util.URL.decodeParam;


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

    @GetMapping(value = "/titlesearch")
    public Flux<PostDTO> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
        return postService.findByTitle(text);
    }

    @GetMapping(value = "/fullsearch")
    public Flux<PostDTO> fullSearch(@RequestParam(value = "text", defaultValue = "") String text,
                                    @RequestParam(value = "minDate", defaultValue = "") String minDate,
                                    @RequestParam(value = "maxDate", defaultValue = "") String maxDate) throws UnsupportedEncodingException, ParseException {
        text = decodeParam(text);
        Instant min = convertDate(minDate, Instant.EPOCH);
        Instant max = convertDate(maxDate, Instant.now());

        return postService.fullSearch(text, min, max);
    }

    @GetMapping(value = "/user/{id}")
    public Flux<PostDTO> findByUser(@PathVariable String id) {
        return postService.findByUser(id);
    }
}
