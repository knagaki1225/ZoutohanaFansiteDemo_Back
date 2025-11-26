package com.example.zoutohanafansitedemo.entity.project;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectTopPageEnd {
    long id;
    private String urlKey;
    private String name;
    private LocalDateTime projectStartAt;
    private LocalDateTime projectEndAt;

    public ProjectTopPageEnd(long id, String urlKey, String name, LocalDateTime projectStartAt, LocalDateTime projectEndAt) {
        this.id = id;
        this.urlKey = urlKey;
        this.name = name;
        this.projectStartAt = projectStartAt;
        this.projectEndAt = projectEndAt;
    }
}
