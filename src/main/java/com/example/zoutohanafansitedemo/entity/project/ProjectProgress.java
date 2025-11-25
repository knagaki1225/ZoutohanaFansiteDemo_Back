package com.example.zoutohanafansitedemo.entity.project;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectProgress {
    long id;
    private String urlKey;
    private String name;
    private String logoUrl;
    private LocalDateTime projectStartAt;
    private LocalDateTime projectEndAt;
    private String introduction;

    public ProjectProgress(long id, String urlKey, String name, String logoUrl, LocalDateTime projectStartAt, LocalDateTime projectEndAt, String introduction) {
        this.id = id;
        this.urlKey = urlKey;
        this.name = name;
        this.logoUrl = logoUrl;
        this.projectStartAt = projectStartAt;
        this.projectEndAt = projectEndAt;
        this.introduction = introduction;
    }
}
