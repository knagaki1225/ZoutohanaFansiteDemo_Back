package com.example.zoutohanafansitedemo.repository;

import com.example.zoutohanafansitedemo.entity.project.Project;
import com.example.zoutohanafansitedemo.mapper.ProjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectRepository {
    private final ProjectMapper projectMapper;

    public ProjectRepository(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    public List<Project> selectAll() {
        return projectMapper.selectAll();
    }

    public Project findById(long id) {
        return projectMapper.findById(id);
    }

    public List<Project> selectFourEndProjects(){
        return projectMapper.selectFourEndProjects();
    }

    public List<Project> selectProgressProjects(){
        return projectMapper.selectProgressProjects();
    }

    public List<Project> selectEndProjects(){
        return projectMapper.selectEndProjects();
    }

    public int insert(Project project) {
        return projectMapper.insert(project);
    }

    public Project selectByUrlKey(String urlKey){
        return projectMapper.findByUrlKey(urlKey);
    }
}
