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

    public List<Post> selectAll() {
        return postMapper.selectAll();
    }

    public Post findById(long id) {
        return postMapper.findById(id);
    }

    public List<PostTop> fetchTopLatest() {
        return postMapper.fetchTopLatest();
    }

    public List<PostTop> fetchTopCategory(int category) {
        return postMapper.fetchTopCategory(category);
    }

    public List<PostList> fetchListCategory(int category) {
        return postMapper.fetchListCategory(category);
    }

    public PostView selectById(long id) {
        return postMapper.selectById(id);
    }
}
