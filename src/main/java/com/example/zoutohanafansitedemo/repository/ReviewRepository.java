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

    public List<ReviewView> selectByProjectId(long projectId) {
        return reviewMapper.selectByProjectId(projectId);
    }

    public List<ReviewView> selectRandomByProjectId(long projectId) {
        return reviewMapper.selectRandomByProjectId(projectId);
    }

    public List<ReviewMypage> selectByUserId(long userId) {
        return reviewMapper.selectByUserId(userId);
    }
}
