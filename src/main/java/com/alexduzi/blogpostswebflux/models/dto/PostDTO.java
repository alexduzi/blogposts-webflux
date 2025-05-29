package com.alexduzi.blogpostswebflux.models.dto;

import com.alexduzi.blogpostswebflux.models.embedded.Author;
import com.alexduzi.blogpostswebflux.models.embedded.Comment;
import com.alexduzi.blogpostswebflux.models.entities.Post;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class PostDTO {

    private String id;
    private Instant moment;
    private String title;
    private String body;

    private Author author;

    private List<Comment> comments = new ArrayList<>();

    public PostDTO() {
    }

    public PostDTO(Post post) {
        this.id = post.getId();
        this.moment = post.getMoment();
        this.title = post.getTitle();
        this.body = post.getBody();
        this.author = post.getAuthor();
        this.comments.addAll(post.getComments());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public Post toEntity() {
        Post post = new Post();
        post.setId(this.id);
        post.setMoment(this.moment);
        post.setTitle(this.title);
        post.setBody(this.body);
        post.setAuthor(this.author);
        return post;
    }
}
