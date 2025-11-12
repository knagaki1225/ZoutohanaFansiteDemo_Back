package com.example.zoutohanafansitedemo.service;

import com.example.zoutohanafansitedemo.entity.Project;
import com.example.zoutohanafansitedemo.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getFourEndProject(){
        return projectRepository.selectFourEndProjects();
    }

    public List<Project> getProgressProjects(){
        return projectRepository.selectProgressProjects();
    }
}
