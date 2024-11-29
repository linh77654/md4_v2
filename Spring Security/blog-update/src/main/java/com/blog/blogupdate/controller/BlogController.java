package com.blog.blogupdate.controller;

import com.blog.blogupdate.model.Blog;
import com.blog.blogupdate.model.Category;
import com.blog.blogupdate.service.IBlogService;
import com.blog.blogupdate.service.iml.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private IBlogService blogService;

    @Autowired
    private CategoryService categoryService;

//    @GetMapping
//    public String showList(@RequestParam(value = "page", defaultValue = "0") int page,
//                           @RequestParam(value = "sort", defaultValue = "") String sortTime,
//                           @RequestParam(value = "category", defaultValue = "0") int category,
//                           Model model) {
//        Page<Blog> blogList;
//        switch (category) {
//            case 1:
//            case 2:
//            case 3:
//            case 4:
//                blogList = blogService.findByCategory(category, PageRequest.of(page, 3));
//                break;
//            default:
//                if (sortTime.equals("")) {
//                    blogList = blogService.findAll(PageRequest.of(page, 3));
//                } else if (sortTime.equals("asc")) {
//                    Sort sort = Sort.by("createDate").ascending();
//                    blogList = blogService.findAllSort( PageRequest.of(page, 3, sort));
//                } else {
//                    Sort sort = Sort.by("createDate").descending();
//                    blogList = blogService.findAllSort( PageRequest.of(page, 3, sort));
//                }
//                break;
//        }
//
//        List<Category> categories = categoryService.findAll();
//        model.addAttribute("categorys", categories);
//        model.addAttribute("blogList", blogList);
//        return "views/home";
//    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("blog", new Blog());
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categorys", categories);
        return "views/create";
    }

    @PostMapping("/create")
    public String save(@ModelAttribute("blog") Blog blog) {
        blogService.save(blog);
        return "redirect:/blog";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable int id, Model model) {
        model.addAttribute("blog", blogService.findById(id));
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categorys", categories);
        return "views/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("customer") Blog blog, RedirectAttributes redirectAttributes) {
        blogService.save(blog);
        redirectAttributes.addFlashAttribute("message", "Blog save successfully");
        return "redirect:/blog";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, Model model) {
        Optional<Blog> blog = blogService.findById(id);
        model.addAttribute("blog", blog.get());
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categorys", categories);
        return "views/delete";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute("customer") Blog blog, RedirectAttributes redirect) {
        blogService.remove(blog.getId());
        redirect.addFlashAttribute("message", "Blog deleted successfully");
        return "redirect:/blog";
    }

    @GetMapping("/{id}/view")
    public String view(@PathVariable int id, Model model) {
        Optional<Blog> blog = blogService.findById(id);
        model.addAttribute("blog", blog.get());
        return "views/viewdetail";
    }
}