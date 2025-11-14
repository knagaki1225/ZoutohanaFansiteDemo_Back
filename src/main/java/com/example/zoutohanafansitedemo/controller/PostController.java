package com.example.zoutohanafansitedemo.controller;

import com.example.zoutohanafansitedemo.entity.post.*;
import com.example.zoutohanafansitedemo.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<Post>> selectAll() {
        List<Post> posts = postService.selectAll();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable long id) {
        Post post = postService.findById(id);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/top/latest")
    public ResponseEntity<List<PostTop>> fetchTopLatest() {
        List<PostTop> postTops = postService.fetchTopLatest();
        return ResponseEntity.ok(postTops);
    }

    @GetMapping("/top/{category}")
    public ResponseEntity<List<PostTop>> fetchTopCategory(@PathVariable int category) {
        List<PostTop> postTops = postService.fetchTopCategory(category);
        return ResponseEntity.ok(postTops);
    }

//    @GetMapping("/list/{category}")
//    public ResponseEntity<List<PostList>> fetchListCategory(@PathVariable int category) {
//        List<PostList> postLists = postService.fetchListCategory(category);
//        return ResponseEntity.ok(postLists);
//    }

    @GetMapping("/view/{id}")
    public ResponseEntity<PostView> selectById(@PathVariable long id) {
        PostView postView = postService.selectById(id);
        return ResponseEntity.ok(postView);
    }

    @GetMapping("/list/{category}")
    public ResponseEntity<PostPagination> fetchPostPagination(@PathVariable int category, @RequestParam(defaultValue = "1") int page){
        return ResponseEntity.ok(postService.getPostPagination(category, page));
    }
}
