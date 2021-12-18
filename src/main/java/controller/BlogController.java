package controller;

import model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.blog.BlogService;

import java.util.Optional;

@Controller
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping("")
    public String showListBlog(Model model) {
        Iterable<Blog> blogs = blogService.findAll();
        model.addAttribute("listBlog", blogs);
        return "/blog/list";
    }

    @GetMapping("/create-blog")
    public String showCreate() {
        return "/blog/create";
    }

    @PostMapping("/create")
    public String create(Blog blog) {
        blogService.save(blog);
        return "redirect:/blogs";
    }

    @GetMapping("/edit-blog/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Blog> blogOptional = blogService.findById(id);
        Blog blog1 = blogOptional.get();
        model.addAttribute("blog", blog1);
        return "/blog/edit";
    }

    @PostMapping("/edit-blog")
    public String update(Blog blog) {
        blogService.save(blog);
        return "redirect:/blogs";
    }

    @GetMapping("/delete-blog/{id}")
    public String showDelete(@PathVariable Long id, Model model) {
        Optional<Blog> blog = blogService.findById(id);
        if (blog != null) {
            Blog blog1 = blog.get();
            model.addAttribute("delete", blog1);
            return "/blog/delete";
        } else {
            return "error";
        }
    }
    @PostMapping("/delete-blog")
    public String deleteBlog(Blog blog) {
        blogService.remove(blog.getId());
        return "redirect:/blogs";
    }
}
