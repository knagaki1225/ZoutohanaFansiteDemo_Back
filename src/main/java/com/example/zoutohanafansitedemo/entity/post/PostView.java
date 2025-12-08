package com.example.zoutohanafansitedemo.entity.post;

import com.example.zoutohanafansitedemo.entity.enums.PostCategory;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostView {
    private long id;
    private PostCategory category;    // enums/PostCategory
    private String title;
    private String content;
    private LocalDateTime postedAt;

    public PostView(long id, PostCategory category, String title, String content, LocalDateTime postedAt) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.content = content;
        this.postedAt = postedAt;
    }
}
