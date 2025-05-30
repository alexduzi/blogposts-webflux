package com.alexduzi.blogpostswebflux.models.entities;

import com.alexduzi.blogpostswebflux.models.embedded.Author;
import com.alexduzi.blogpostswebflux.models.embedded.Comment;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "posts")
public class Post {

    @Id
    private String id;
    private Instant moment;
    private String title;
    private String body;
    private Author author;

    private List<Comment> comments = new ArrayList<>();

    @DocumentReference
    private User user;

    public Post() {
    }

    public Post(String id, Instant moment, String title, String body, Author author) {
        this.id = id;
        this.moment = moment;
        this.title = title;
        this.body = body;
        this.author = author;
    }

    public Post(String id, Instant moment, String title, String body, String authorId, String authorName) {
        this.id = id;
        this.moment = moment;
        this.title = title;
        this.body = body;
        this.author = new Author(authorId, authorName);
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

    public void setMoment(Instant date) {
        this.moment = date;
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

    public String getAuthorId() {
        return author.getId();
    }

    public String getAuthorName() {
        return author.getName();
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void addComment(String CommentText, Instant commentDate, String authorId, String authorName) {
        Comment comment = new Comment(CommentText, moment, authorId, authorName);
        comments.add(comment);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, body, moment, id, title);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Post other = (Post) obj;
        return Objects.equals(author, other.author) && Objects.equals(body, other.body) && Objects.equals(moment, other.moment) && Objects.equals(id, other.id) && Objects.equals(title, other.title);
    }
}
