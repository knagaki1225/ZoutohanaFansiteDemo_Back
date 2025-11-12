package com.example.zoutohanafansitedemo.controller;

import com.example.zoutohanafansitedemo.entity.post.*;
import com.example.zoutohanafansitedemo.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<Post>> findAll() {
        List<Post> posts = postService.findAll();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable long id) {
        Post post = postService.findById(id);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/top/latest")
    public ResponseEntity<List<PostTop>> fetchTopLatest() {
        List<PostTop> posts = postService.fetchTopLatest();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/top/{category}")
    public ResponseEntity<List<PostTop>> fetchTopCategory(@PathVariable int category) {
        List<PostTop> posts = postService.fetchTopCategory(category);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/list/{category}")
    public ResponseEntity<List<PostList>> fetchListCategory(@PathVariable int category) {
        List<PostList> posts = postService.fetchListCategory(category);
        return ResponseEntity.ok(posts);
    }
}
