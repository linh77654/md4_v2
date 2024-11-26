package com.example.demo.service;


import com.example.demo.model.Blog;
import com.example.demo.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBlogService {
    Page<Blog> findAllWithTitleFilter(String title, Pageable pageable);


    Blog findById(long id);
    void save(Blog blog);
    void deleteById(long id);
    List<Blog> searchByTitle(String title);
    Page<Blog> findAllByCategory(Category category, Pageable pageable);
}