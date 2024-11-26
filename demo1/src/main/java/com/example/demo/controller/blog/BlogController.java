package com.example.demo.controller.blog;


import com.example.demo.model.Blog;
import com.example.demo.model.Category;
import com.example.demo.service.IBlogService;
import com.example.demo.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private IBlogService blogService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/delete")
    public String deleteBlog(@RequestParam long id, RedirectAttributes redirectAttributes) {
        blogService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Deleted blog successfully!");
        return "redirect:/blog";
    }

    @GetMapping("/detail")
    public String editBlog(@RequestParam long id, Model model) {
        model.addAttribute("blog", blogService.findById(id));
        return "blog/detail";
    }


    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("blog", new Blog());
        return "blog/save";
    }

    @PostMapping("/save")
    public String saveBlog(@RequestParam long categoryId, Blog blog, RedirectAttributes redirectAttributes) {
        blog.setCategory(categoryService.findById(categoryId));
        blogService.save(blog);
        redirectAttributes.addFlashAttribute("message", "Saved blog successfully!");
        return "redirect:/blog";
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam long id, Model model) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("blog", blogService.findById(id));
        return "blog/save";
    }

    @GetMapping("")
    public String getAllBlogs(@RequestParam(required = false, defaultValue = "0") int page,
                              @RequestParam(required = false, defaultValue = "") String searchTitle,
                              Model model) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt").and(Sort.by(Sort.Direction.DESC, "title"));
        Pageable pageable = PageRequest.of(page, 2, sort);
        Page<Blog> blogs = blogService.findAllWithTitleFilter( searchTitle,  pageable);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("blogs", blogs);
        model.addAttribute("searchTitle", searchTitle);
        return "blog/list";

    }

    @GetMapping("/searchByCategory")
    public String searchBlog(@RequestParam Long categoryId,
                             @RequestParam(required = false, defaultValue = "0") int page,
                             Model model) {
        Pageable pageable = PageRequest.of(page, 2);

        Category category = categoryService.findById(categoryId);

        Page<Blog> blogs = blogService.findAllByCategory(category, pageable);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("blogs", blogs);
        return "blog/list";
    }
}
