package com.example.zoutohanafansitedemo.entity.project;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectList {
    long id;
    private String urlKey;
    private String name;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String introduction;

    public ProjectList(long id, String urlKey, String name, LocalDateTime startAt, LocalDateTime endAt, String introduction) {
        this.id = id;
        this.urlKey = urlKey;
        this.name = name;
        this.startAt = startAt;
        this.endAt = endAt;
        this.introduction = introduction;
    }
}
