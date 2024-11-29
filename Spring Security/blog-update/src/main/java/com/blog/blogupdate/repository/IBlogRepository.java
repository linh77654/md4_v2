package com.blog.blogupdate.repository;

import com.blog.blogupdate.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface IBlogRepository extends JpaRepository<Blog, Long> {
    Page<Blog> findAllByTitleContaining(String title, Pageable pageable);

    @Query("SELECT b FROM Blog b")
    Page<Blog> findAllSort(Pageable pageable);


    @Query(value = "SELECT * FROM blog b WHERE b.category = :id", nativeQuery = true)
    Page<Blog> findAllByCategory(@Param("id") Long id, Pageable pageable);

    @Query(value = "SELECT * FROM blog b WHERE b.category = :category", nativeQuery = true)
    List<Blog> findAllBlogByCategory(@Param("category") Long id);

    @Query(value = "SELECT * FROM blog b WHERE b.title LIKE %:search%", nativeQuery = true)
    List<Blog> findAllByTitleContaining(@Param("search") String search);

}
