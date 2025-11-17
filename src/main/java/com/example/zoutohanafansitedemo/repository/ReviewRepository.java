package com.example.zoutohanafansitedemo.repository;

import com.example.zoutohanafansitedemo.entity.review.*;
import com.example.zoutohanafansitedemo.mapper.ReviewMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewRepository {
    private final ReviewMapper reviewMapper;

    public ReviewRepository(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    public List<Review> selectAll() {
        return reviewMapper.selectAll();
    }

    public Review findById(long id) {
        return reviewMapper.findById(id);
    }

    public List<Review> selectByProjectId(long projectId) {
        return reviewMapper.selectByProjectId(projectId);
    }

    public List<Review> selectRandomByProjectId(long projectId) {
        return reviewMapper.selectRandomByProjectId(projectId);
    }
}
