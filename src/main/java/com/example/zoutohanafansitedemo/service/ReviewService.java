package com.example.zoutohanafansitedemo.service;

import com.example.zoutohanafansitedemo.entity.review.*;
import com.example.zoutohanafansitedemo.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> selectAll() {
        return reviewRepository.selectAll();
    }

    public Review findById(long id) {
        return reviewRepository.findById(id);
    }

    public List<Review> selectByProjectId(long projectId) {
        return reviewRepository.selectByProjectId(projectId);
    }
}
