package com.example.zoutohanafansitedemo.controller;

import com.example.zoutohanafansitedemo.entity.review.*;
import com.example.zoutohanafansitedemo.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

//    ==========ここからデバッグ用(削除済み・非表示も返す)==========
    @GetMapping
    public ResponseEntity<List<Review>> selectAll() {
        List<Review> reviews = reviewService.selectAll();
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> findById(@PathVariable long id) {
        Review review = reviewService.findById(id);
        return ResponseEntity.ok(review);
    }
//    ==========ここまでデバッグ用(削除済み・非表示も返す)==========

    @GetMapping("/byProject/{projectId}")
    public ResponseEntity<List<ReviewView>> selectByProjectId(@PathVariable long projectId, @RequestParam(required = false) String mode) {
        List<ReviewView> reviews;
        if("karuta".equals(mode)) {
            reviews = reviewService.selectRandomByProjectId(projectId);
        } else {
            reviews = reviewService.selectByProjectId(projectId);
        }

        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/byUser/{userId}")
    public ResponseEntity<List<ReviewMypage>> selectByUserId(@PathVariable long userId) {
        List<ReviewMypage> reviews = reviewService.selectByUserId(userId);
        return ResponseEntity.ok(reviews);
    }

    @PostMapping
    public ResponseEntity<Review> insert(@Valid @RequestBody Review review,
                                         @AuthenticationPrincipal CustomUserDetails userDetails,
                                         UriComponentsBuilder uriComponentsBuilder) {
        Review createdReview = reviewService.insert(review);
        URI location = uriComponentsBuilder.path("/api/reviews/{id}")
                .buildAndExpand(createdReview.getId()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return ResponseEntity.created(location).body(createdReview);
    }
}
