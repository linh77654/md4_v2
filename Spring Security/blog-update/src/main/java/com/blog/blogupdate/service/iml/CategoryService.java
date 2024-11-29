package com.blog.blogupdate.service.iml;

import com.blog.blogupdate.model.Category;
import com.blog.blogupdate.repository.ICategoryRepository;
import com.blog.blogupdate.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
