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
    public ResponseEntity<List<Review>> selectAll() {
        List<Review> reviews = reviewService.selectAll();
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/byProject/{projectId}")
    public ResponseEntity<List<ReviewView>> selectByProjectId(@PathVariable long projectId) {
        List<ReviewView> reviews = reviewService.selectByProjectId(projectId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/byUser/{userId}")
    public ResponseEntity<List<ReviewMypage>> selectByUserId(@PathVariable long userId) {
        List<ReviewMypage> reviews = reviewService.selectByUserId(userId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> findById(@PathVariable long id) {
        Review review = reviewService.findById(id);
        return ResponseEntity.ok(review);
    }

    @GetMapping("/random/{projectId}")
    public ResponseEntity<List<ReviewView>> selectRandomByProjectId(@PathVariable long projectId) {
        List<ReviewView> reviews = reviewService.selectRandomByProjectId(projectId);
        return ResponseEntity.ok(reviews);
    }
}
