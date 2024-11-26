package com.example.demo.service;




import com.example.demo.model.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
    void save(Category category);
    Category findById(long id);
    void deleteById(long id);
}