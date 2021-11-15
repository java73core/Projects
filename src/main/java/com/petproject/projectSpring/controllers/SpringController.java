package com.petproject.projectSpring.controllers;

import com.petproject.projectSpring.models.Post;
import com.petproject.projectSpring.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class SpringController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/main")
    public String main(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "main";
    }

    @GetMapping("/main/add")
    public String mainAdd(Model model) {
        return "main-add";
    }

    @PostMapping("/main/add")
    public String mainPostAdd(@RequestParam String title, @RequestParam String anons, @RequestParam String fullText, Model model) {
        Post post = new Post(title, anons,fullText);
        postRepository.save(post);
        return "redirect:/main";
    }

    @GetMapping("/main/{id}")
    public String mainDetails(@PathVariable(value = "id") long id, Model model) {
    if(!postRepository.existsById(id)){
          return "redirect:/main";
      }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "main-details";
    }

    @GetMapping("/main/{id}/edit")
    public String mainEdit(@PathVariable(value = "id") long id, Model model) {
        if(!postRepository.existsById(id)){
            return "redirect:/main";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "main-edit";
    }

    @PostMapping("/main/{id}/edit")
    public String mainPostUpdata(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String anons, @RequestParam String fullText, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAnons(anons);
        post.setFullText(fullText);
        postRepository.save(post);
        return "redirect:/main";
    }

    @PostMapping("/main/{id}/delete")
    public String mainPostDelete(@PathVariable(value = "id") long id, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "redirect:/main";
    }
}
