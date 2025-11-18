package com.example.zoutohanafansitedemo.entity.post;

import com.example.zoutohanafansitedemo.entity.enums.PostCategory;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostTop {
    private long id;
    private PostCategory category;    // enums/PostCategory
    private String title;
    private LocalDateTime postedAt;
}
