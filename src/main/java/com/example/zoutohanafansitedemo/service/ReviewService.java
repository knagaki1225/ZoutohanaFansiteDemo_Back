package com.example.zoutohanafansitedemo.service;

import com.example.zoutohanafansitedemo.entity.review.*;
import com.example.zoutohanafansitedemo.mapper.ReviewMapper;
import com.example.zoutohanafansitedemo.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public ReviewService(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    public List<Review> selectAll() {
        return reviewRepository.selectAll();
    }

    public Review findById(long id) {
        return reviewRepository.findById(id);
    }

    public List<ReviewView> selectByProjectId(long projectId) {
        return reviewRepository.selectByProjectId(projectId);
    }

    public List<ReviewView> selectRandomByProjectId(long projectId) {
        return reviewRepository.selectRandomByProjectId(projectId);
    }

    public List<ReviewMypage> selectByUserId(long userId) {
        return reviewRepository.selectByUserId(userId);
    }

    public Review insert(Review review) {
        reviewRepository.insert(review);
        return review;
    }
}
