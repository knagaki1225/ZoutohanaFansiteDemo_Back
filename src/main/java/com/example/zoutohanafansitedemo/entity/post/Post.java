package com.example.zoutohanafansitedemo.entity.post;

import com.example.zoutohanafansitedemo.entity.enums.PostCategory;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Post {
    private long id;
    private int adminId;
    private PostCategory category;    // enums/PostCategory
    private String title;
    private String content;
    private LocalDateTime postedAt;
    private String status;  // enums/PostStatus
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean deleted;
}
