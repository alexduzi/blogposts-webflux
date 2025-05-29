package com.alexduzi.blogpostswebflux.models.entities;

import com.alexduzi.blogpostswebflux.models.dto.PostDTO;
import com.alexduzi.blogpostswebflux.models.embedded.Author;
import com.alexduzi.blogpostswebflux.models.embedded.Comment;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "posts")
public class Post {

    @Id
    private String id;
    private Instant moment;
    private String title;
    private String body;

    private Author author;

    private List<Comment> comments = new ArrayList<>();

    public Post() {
    }

    public Post(String id, Instant moment, String title, String body, Author author) {
        this.id = id;
        this.moment = moment;
        this.title = title;
        this.body = body;
        this.author = author;
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

    public PostDTO toDto() {
        PostDTO postDto = new PostDTO();
        postDto.setId(this.id);
        postDto.setMoment(this.moment);
        postDto.setTitle(this.title);
        postDto.setBody(this.body);
        postDto.setAuthor(this.author);
        return postDto;
    }

    public Post copyFrom(PostDTO postDTO) {
        this.setAuthor(postDTO.getAuthor());
        this.setBody(postDTO.getBody());
        this.setTitle(postDTO.getTitle());
        this.setMoment(postDTO.getMoment());
        return this;
    }
}
