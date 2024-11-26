package com.example.demo.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Blog {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;
    private String title;
    private String content;
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}