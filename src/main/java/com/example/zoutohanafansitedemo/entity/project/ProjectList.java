package com.example.zoutohanafansitedemo.entity.project;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectList {
    long id;
    private String urlKey;
    private String name;
    private LocalDateTime projectStartAt;
    private LocalDateTime projectEndAt;
    private String introduction;

    public ProjectList(long id, String urlKey, String name, LocalDateTime projectStartAt, LocalDateTime projectEndAt, String introduction) {
        this.id = id;
        this.urlKey = urlKey;
        this.name = name;
        this.projectStartAt = projectStartAt;
        this.projectEndAt = projectEndAt;
        this.introduction = introduction;
    }
}
