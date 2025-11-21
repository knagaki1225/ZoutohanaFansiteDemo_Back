package com.example.zoutohanafansitedemo.service;

import com.example.zoutohanafansitedemo.entity.info.PaginationInfo;
import com.example.zoutohanafansitedemo.entity.project.Project;
import com.example.zoutohanafansitedemo.entity.project.ProjectList;
import com.example.zoutohanafansitedemo.entity.project.ProjectPagination;
import com.example.zoutohanafansitedemo.exception.InvalidPaginationException;
import com.example.zoutohanafansitedemo.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAll() {
        return projectRepository.selectAll();
    }

    public List<Project> getFourEndProject(){
        return projectRepository.selectFourEndProjects();
    }

    public List<Project> getProgressProjects(){
        return projectRepository.selectProgressProjects();
    }

    public ProjectPagination getProjectPagination(int page){
        if(page < 1){
            throw new InvalidPaginationException("Invalid page number");
        }

        List<Project> projects = projectRepository.selectEndProjects();
        List<ProjectList> projectLists = new ArrayList<>();

        int size = projects.size();
        int pageSize = size / 5;
        if(size % 5 != 0){
            pageSize++;
        }

        while(page > pageSize){
            page--;
        }

        // 全件のcount番目からnum番目まで取得する
        // 3ページまでしかないのに4ページ目をリクエストされた場合は3ページ目に自動的に変える
        int count = (page - 1) * 5;
        int num = projects.size();
        if(count + 4 <= num){
            num = count + 5;
        }

        PaginationInfo paginationInfo = new PaginationInfo(page, pageSize);

        for(int i = count; i < num; i++){
            Project project = projects.get(i);
            projectLists.add(new ProjectList(project.getId(), project.getUrlKey(), project.getName(), project.getStartAt(), project.getEndAt(), project.getIntroduction()));
        }

        return new ProjectPagination(paginationInfo, projectLists);
    }
}
