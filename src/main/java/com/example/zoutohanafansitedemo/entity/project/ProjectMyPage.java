package com.example.zoutohanafansitedemo.entity.project;

import com.example.zoutohanafansitedemo.entity.enums.ProjectStatus;
import lombok.Data;

@Data
public class ProjectMyPage {
    private long id;
    private String name;
    private ProjectStatus projectStatus;
    private Integer lastDate;
}
