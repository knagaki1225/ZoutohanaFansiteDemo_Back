package com.example.zoutohanafansitedemo.entity.project;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectProgress {
    long id;
    private String urlKey;
    private String name;
    private String logoUrl;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String introduction;

    public ProjectProgress(long id, String urlKey, String name, String logoUrl, LocalDateTime startAt, LocalDateTime endAt, String introduction) {
        this.id = id;
        this.urlKey = urlKey;
        this.name = name;
        this.logoUrl = logoUrl;
        this.startAt = startAt;
        this.endAt = endAt;
        this.introduction = introduction;
    }
}
