package com.gl.model;

import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Post() {}

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // Getters and Setters

    @Override
    public String toString() {
        return "Post{id=" + id + ", title='" + title + "', content='" + content + "'}";
    }

    public void setUser(User user) {
        this.user = user;
    }

}
