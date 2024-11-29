package com.blog.blogupdate.repository;

import com.blog.blogupdate.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
