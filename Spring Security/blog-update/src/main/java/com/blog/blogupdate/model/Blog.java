package com.blog.blogupdate.model;

import jakarta.persistence.*;

@Entity
@Table(name = "blog")
public class Blog extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;
    private String author;
    @Column(name = "description", columnDefinition = "LONGTEXT")
    private String description;


    @ManyToOne
    @JoinColumn(name = "category_ìd")
    private Category category;


    public Blog() {
    }

    public Blog(int id, String title, String content, String author, String description) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.description = description;
    }
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
