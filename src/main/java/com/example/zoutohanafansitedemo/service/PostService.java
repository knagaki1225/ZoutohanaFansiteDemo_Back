package com.example.zoutohanafansitedemo.service;

import com.example.zoutohanafansitedemo.entity.post.*;
import com.example.zoutohanafansitedemo.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;


    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findById(long id) {
        return postRepository.findById(id);
    }

    public List<PostTop> fetchTopLatest() {
        return postRepository.fetchTopLatest();
    }

    public List<PostTop> fetchTopCategory(int category) {
        return postRepository.fetchTopCategory(category);
    }

    public List<PostList> fetchListCategory(int category) {
        return postRepository.fetchListCategory(category);
    }

    public PostView fetchById(long id) {
        return postRepository.fetchById(id);
    }
}
