package com.blog.blogupdate.controller;

import com.blog.blogupdate.model.Blog;
import com.blog.blogupdate.model.Category;
import com.blog.blogupdate.service.IBlogService;
import com.blog.blogupdate.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/blogs")
public class RestBlogController {

    @Autowired
    private IBlogService blogService;

    @GetMapping
    public ResponseEntity<?> getAllBlog() {
        List<Blog> blogs = blogService.findAllBlog();
        if (blogs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(blogs,HttpStatus.OK);
    }

    @GetMapping("category/{id}")
    public ResponseEntity<?> getBlogByCategoryId(@PathVariable int categoryId) {
        List<Blog> blogs = blogService.findAllBlogByCategory(categoryId);
        if (blogs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(blogs,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBlogById(@PathVariable int id) {
        Blog blog = blogService.findById(id).get();
        if (blog == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(blog,HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getBlogBySearch(@RequestParam("search") String search) {
        List<Blog> blogs = blogService.findAllBlogTitle(search);
        if (blogs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(blogs,HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> getBlogs(@RequestParam("page") int page, @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        List<Blog> blogs = blogService.getBlogs(pageable);
        return new ResponseEntity<>(blogs,HttpStatus.OK);
    }

}
