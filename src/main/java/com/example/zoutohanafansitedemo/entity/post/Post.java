package com.example.zoutohanafansitedemo.entity.post;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Post {
    private long id;
    private int adminId;
    private int category;
    private String title;
    private String content;
    private LocalDateTime postedAt;
    private int status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
