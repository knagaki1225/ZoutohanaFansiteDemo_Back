package com.example.zoutohanafansitedemo.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Post {
    private Integer id;
    private Integer adminId;
    private Integer category;
    private String title;
    private String content;
    private LocalDateTime postedAt;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isDelete;
}
