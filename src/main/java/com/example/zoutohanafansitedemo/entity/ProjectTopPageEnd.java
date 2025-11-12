package com.example.zoutohanafansitedemo.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectTopPageEnd {
    long id;
    private String urlKey;
    private String name;
    private LocalDateTime startAt;
    private LocalDateTime endAt;

    public ProjectTopPageEnd(long id, String urlKey, String name, LocalDateTime startAt, LocalDateTime endAt) {
        this.id = id;
        this.urlKey = urlKey;
        this.name = name;
        this.startAt = startAt;
        this.endAt = endAt;
    }
}
