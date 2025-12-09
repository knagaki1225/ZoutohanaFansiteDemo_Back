package com.example.zoutohanafansitedemo.service;

import com.example.zoutohanafansitedemo.entity.fansite.FanSiteTop;
import com.example.zoutohanafansitedemo.entity.post.PostTop;
import com.example.zoutohanafansitedemo.entity.project.Project;
import com.example.zoutohanafansitedemo.entity.project.ProjectProgress;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FanSiteService {
    private final PostService postService;
    private final ProjectService projectService;

    public FanSiteService(PostService postService, ProjectService projectService) {
        this.postService = postService;
        this.projectService = projectService;
    }

    public FanSiteTop getFanSiteTopInfo(){
        FanSiteTop fanSiteTop = new FanSiteTop();

        List<PostTop> posts = postService.getTopLatest();
        fanSiteTop.setPosts(posts);

        List<Project> projects = projectService.getProgressProjects();
        List<ProjectProgress> projectProgresses = new ArrayList<>();
        for(Project p:projects) {
            projectProgresses.add(new ProjectProgress(p.getId(), p.getUrlKey(), p.getName(), p.getMainImgUrl(), p.getThemeColor(), p.getProjectStartAt(), p.getProjectEndAt(), p.getIntroduction()));
        }

        fanSiteTop.setProjects(projectProgresses);

        return fanSiteTop;
    }
}
