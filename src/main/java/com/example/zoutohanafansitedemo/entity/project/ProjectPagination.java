package com.example.zoutohanafansitedemo.entity.project;

import com.example.zoutohanafansitedemo.entity.info.PaginationInfo;
import lombok.Data;

import java.util.List;

@Data
public class ProjectPagination {
    private PaginationInfo paginationInfo;
    private List<ProjectList> projectList;

    public ProjectPagination(PaginationInfo paginationInfo, List<ProjectList> projectList) {
        this.paginationInfo = paginationInfo;
        this.projectList = projectList;
    }
}