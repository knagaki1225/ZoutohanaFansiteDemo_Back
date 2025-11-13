package com.example.zoutohanafansitedemo.service;

import com.example.zoutohanafansitedemo.entity.project.Project;
import com.example.zoutohanafansitedemo.entity.project.ProjectListPageEnd;
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

    public List<Project> getFourEndProject(){
        return projectRepository.selectFourEndProjects();
    }

    public List<Project> getProgressProjects(){
        return projectRepository.selectProgressProjects();
    }

    public List<ProjectListPageEnd> getProjectListPageEnd(int page){
        List<Project> projects = projectRepository.selectEndProjects();
        List<ProjectListPageEnd> projectListPageEnds = new ArrayList<>();

        // 全件のcount番目からnum番目まで取得する
        // 3ページまでしかないのに4ページ目をリクエストされた場合は3ページ目に自動的に変える
        int count = (page - 1) * 5;
        int num = projects.size();
        while(true){
            if(num < count){
                count -= 5;
                continue;
            }
            if(count + 4 <= num){
                num = count + 5;
            }
            break;
        }

        for(int i = count; i < num; i++){
            Project project = projects.get(i);
            projectListPageEnds.add(new ProjectListPageEnd(project.getId(), project.getUrlKey(), project.getName(), project.getStartAt(), project.getEndAt(), project.getIntroduction()));
        }

        return  projectListPageEnds;
    }
}
