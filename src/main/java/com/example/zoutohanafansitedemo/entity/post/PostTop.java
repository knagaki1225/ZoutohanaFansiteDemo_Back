package com.example.zoutohanafansitedemo.entity.post;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostTop {
    private Integer id;
    private Integer category;
    private String title;
    private LocalDateTime postedAt;
}
