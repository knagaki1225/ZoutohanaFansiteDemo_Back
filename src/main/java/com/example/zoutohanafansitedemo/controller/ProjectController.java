package com.example.zoutohanafansitedemo.controller;

import com.example.zoutohanafansitedemo.entity.project.*;
import com.example.zoutohanafansitedemo.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    //    ==========ここからデバッグ用(削除済み・非表示も返す)==========
    @GetMapping
    public ResponseEntity<List<Project>> getAll() {
        List<Project> projects = projectService.getAll();
        return ResponseEntity.ok(projects);
    }
//    ==========ここまでデバッグ用(削除済み・非表示も返す)==========

    @GetMapping("/top/end")
    public ResponseEntity<List<ProjectTopPageEnd>> getTopProjects() {
        List<Project> projects = projectService.getFourEndProject();
        List<ProjectTopPageEnd> topProjects = new ArrayList<>();
        for (Project project : projects) {
            topProjects.add(new ProjectTopPageEnd(project.getId(), project.getUrlKey(), project.getName(), project.getStartAt(), project.getEndAt()));
        }

        return ResponseEntity.ok(topProjects);
    }

    @GetMapping("/top/progress")
    public ResponseEntity<List<ProjectProgress>> getTopProjectsProgress() {
        List<Project> projects = projectService.getProgressProjects();
        List<ProjectProgress> topProjects = new ArrayList<>();
        for (Project project : projects) {
            topProjects.add(new ProjectProgress(project.getId(), project.getUrlKey(), project.getName(), project.getLogoImgUrl(), project.getStartAt(), project.getEndAt(), project.getIntroduction()));
        }

        return ResponseEntity.ok(topProjects);
    }

    @GetMapping("/list")
    public ResponseEntity<ProjectPagination> getListPageProjectEnd(@RequestParam(defaultValue = "1") int page) {
        ProjectPagination pp = projectService.getProjectPagination(page);
        return ResponseEntity.ok(pp);
    }
}