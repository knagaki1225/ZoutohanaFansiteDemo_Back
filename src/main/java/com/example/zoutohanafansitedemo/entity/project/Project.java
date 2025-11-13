package com.example.zoutohanafansitedemo.entity.project;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Project {
    private long id;
    private String name;
    private String urlKey;
    private String introduction;
    private String logoImgUrl;
    private int themeColor;
    private int status;
    private boolean isVisibleBookTitle;
    private boolean isVisibleReviewTitle;
    private boolean isVisibleUserInfo;
    private boolean isPublic;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private LocalDateTime submissionStartAt;
    private LocalDateTime submissionEndAt;
    private LocalDateTime votingStartAt;
    private LocalDateTime votingEndAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
