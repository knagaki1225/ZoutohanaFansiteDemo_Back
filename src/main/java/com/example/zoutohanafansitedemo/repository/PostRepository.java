package com.example.zoutohanafansitedemo.repository;

import com.example.zoutohanafansitedemo.entity.post.*;
import com.example.zoutohanafansitedemo.mapper.PostMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostRepository {
    private final PostMapper postMapper;

    public PostRepository(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    public List<Post> findAll() {
        return postMapper.findAll();
    }

    public Post findById(Integer id) {
        return postMapper.findById(id);
    }

    public List<PostTop> fetchTopLatest() {
        return postMapper.fetchTopLatest();
    }
}
