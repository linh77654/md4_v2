package com.example.demo.dto;


import com.example.demo.model.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
public class BlogDTO {
    private long id;
    private String title;
    private String content;
    private Date createdAt;
    private Category category;
}