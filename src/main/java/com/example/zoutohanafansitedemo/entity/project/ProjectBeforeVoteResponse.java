package com.example.zoutohanafansitedemo.entity.project;

import com.example.zoutohanafansitedemo.entity.enums.ProjectStatus;
import lombok.Data;

@Data
public class ProjectBeforeVoteResponse {
    ProjectStatus status;
    String themeColor;

    public ProjectBeforeVoteResponse(ProjectStatus status, String themeColor) {
        this.status = status;
        this.themeColor = themeColor;
    }
}
