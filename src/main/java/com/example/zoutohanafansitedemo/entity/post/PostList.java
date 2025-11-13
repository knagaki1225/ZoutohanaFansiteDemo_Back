package com.example.zoutohanafansitedemo.entity.post;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostList {
    private long id;
    private String title;
    private String content;
    private LocalDateTime postedAt;
}
