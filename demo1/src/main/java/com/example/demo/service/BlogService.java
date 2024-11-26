package com.example.demo.service;


import com.example.demo.model.Blog;
import com.example.demo.model.Category;
import com.example.demo.repository.IBlogRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService implements IBlogService {
    @Autowired
    private IBlogRepository blogRepository;

    @Override
    public Page<Blog> findAllWithTitleFilter(String title, Pageable pageable) {
        if (title != null && !title.isEmpty()) {
            return blogRepository.findAllByTitleContaining(title, pageable);
        } else {
            return blogRepository.findAll(pageable);
        }
    }

    @Override
    public Blog findById(long id) {
        return blogRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void save(Blog blog) {
        blogRepository.save(blog);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public List<Blog> searchByTitle(String title) {
//        return blogRepository.searchByTitleContaining(title);
        return blogRepository.searchByName("%" + title + "%");
    }


    @Override
    public Page<Blog> findAllByCategory(Category category, Pageable pageable) {
        return blogRepository.findAllByCategory(category, pageable);
    }


}