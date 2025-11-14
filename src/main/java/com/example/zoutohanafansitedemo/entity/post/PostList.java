package com.example.zoutohanafansitedemo.entity.post;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostList {
    private long id;
    private String title;
    private String content;
    private LocalDateTime postedAt;

    public PostList(long id, String title, String content, LocalDateTime postedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.postedAt = postedAt;
    }
}
