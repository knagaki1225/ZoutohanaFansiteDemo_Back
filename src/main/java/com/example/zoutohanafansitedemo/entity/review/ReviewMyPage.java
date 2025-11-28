package com.example.zoutohanafansitedemo.entity.review;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewMyPage {
    private long reviewId;
    private String bookTitle;
    private String bookPublisher;
    private String bookAuthor;
    private String reviewTitle;
    private String reviewContent;
    private Integer voteCount;

    public ReviewMyPage(long reviewId, String bookTitle, String bookPublisher, String bookAuthor, String reviewTitle, String reviewContent, Integer voteCount) {
        this.reviewId = reviewId;
        this.bookTitle = bookTitle;
        this.bookPublisher = bookPublisher;
        this.bookAuthor = bookAuthor;
        this.reviewTitle = reviewTitle;
        this.reviewContent = reviewContent;
        this.voteCount = voteCount;
    }
}
