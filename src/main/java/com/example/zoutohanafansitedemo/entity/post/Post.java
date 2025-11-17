package com.example.zoutohanafansitedemo.entity.post;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Post {
    private long id;
    private int adminId;
    private String category;    // enums/PostCategory
    private String title;
    private String content;
    private LocalDateTime postedAt;
    private String status;  // enums/PostStatus
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
