package com.example.zoutohanafansitedemo.entity.review;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewMypage {
    private long id;
    private long projectId;
    private long userId;
    private long bookIsbn;
    private String bookTitle;
    private String bookPublisher;
    private String bookAuthor;
    private String reviewTitle;
    private String reviewContent;
    private Integer voteCount;
    private LocalDateTime createdAt;
}
