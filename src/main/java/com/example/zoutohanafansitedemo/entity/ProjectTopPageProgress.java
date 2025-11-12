package com.example.zoutohanafansitedemo.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectTopPageProgress {
    long id;
    private String urlKey;
    private String name;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String introduction;

    public ProjectTopPageProgress(long id, String urlKey, String name, LocalDateTime startAt, LocalDateTime endAt, String introduction) {
        this.id = id;
        this.urlKey = urlKey;
        this.name = name;
        this.startAt = startAt;
        this.endAt = endAt;
        this.introduction = introduction;
    }
}
