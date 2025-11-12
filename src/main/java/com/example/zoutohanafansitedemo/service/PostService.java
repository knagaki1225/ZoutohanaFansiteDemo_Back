package com.example.zoutohanafansitedemo.service;

import com.example.zoutohanafansitedemo.entity.Post;
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

    public Post findById(Integer id) {
        return postRepository.findById(id);
    }
}
