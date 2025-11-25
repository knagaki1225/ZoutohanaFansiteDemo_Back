package com.example.zoutohanafansitedemo.entity.project;

import com.example.zoutohanafansitedemo.entity.enums.ProjectStatus;
import com.example.zoutohanafansitedemo.entity.enums.ThemeColor;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Project {
    private long id;

    @NotNull
    private String name;

    @NotNull
    private String urlKey;

    private String introduction;
    private String logoImgUrl;
    private ThemeColor themeColor;  // enums/ThemeColor
    private ProjectStatus status;  // enums/ProjectStatus
    private boolean isVisibleBookTitle;
    private boolean isVisibleReviewTitle;
    private boolean isVisibleUserInfo;
    private boolean isPublic;
    private LocalDateTime projectStartAt;
    private LocalDateTime projectEndAt;
    private LocalDateTime submissionStartAt;
    private LocalDateTime submissionEndAt;
    private LocalDateTime votingStartAt;
    private LocalDateTime votingEndAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
