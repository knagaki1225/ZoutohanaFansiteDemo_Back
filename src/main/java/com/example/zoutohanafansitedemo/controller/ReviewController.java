package com.example.zoutohanafansitedemo.controller;

import com.example.zoutohanafansitedemo.entity.review.*;
import com.example.zoutohanafansitedemo.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> selectReviews(@RequestParam(required = false) Long projectId) {
        List<Review> reviews;

        if (projectId == null) {
            reviews = reviewService.selectAll();
        } else {
            reviews = reviewService.selectByProjectId(projectId);
        }

        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> findById(@PathVariable long id) {
        Review review = reviewService.findById(id);
        return ResponseEntity.ok(review);
    }
}
