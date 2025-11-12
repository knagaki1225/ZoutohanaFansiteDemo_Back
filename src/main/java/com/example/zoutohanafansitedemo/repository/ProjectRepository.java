package com.example.zoutohanafansitedemo.repository;

import com.example.zoutohanafansitedemo.entity.Project;
import com.example.zoutohanafansitedemo.mapper.ProjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectRepository {
    private final ProjectMapper projectMapper;

    public ProjectRepository(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    public List<Project> selectFourEndProjects(){
        return projectMapper.selectFourEndProjects();
    }

    public List<Project> selectProgressProjects(){
        return projectMapper.selectProgressProjects();
    }
}