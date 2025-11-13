package com.example.zoutohanafansitedemo.entity.post;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostView {
    private long id;
    private int category;
    private String title;
    private String content;
    private LocalDateTime postedAt;
}
