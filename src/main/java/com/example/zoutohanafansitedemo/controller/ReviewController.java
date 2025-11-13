package com.example.zoutohanafansitedemo.controller;

import com.example.zoutohanafansitedemo.entity.review.*;
import com.example.zoutohanafansitedemo.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> findAll() {
        List<Review> reviews = reviewService.findAll();
        return ResponseEntity.ok(reviews);
    }
}
