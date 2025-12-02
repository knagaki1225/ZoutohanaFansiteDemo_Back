package com.example.zoutohanafansitedemo.controller;

import com.example.zoutohanafansitedemo.entity.project.*;
import com.example.zoutohanafansitedemo.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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

    @GetMapping("/{id}")
    public ResponseEntity<Project> findById(@PathVariable long id) {
        Project project = projectService.findById(id);
        return ResponseEntity.ok(project);
    }
//    ==========ここまでデバッグ用(削除済み・非表示も返す)==========

    @GetMapping("/top/end")
    public ResponseEntity<List<ProjectTopPageEnd>> getTopProjects() {
        List<Project> projects = projectService.getFourEndProject();
        List<ProjectTopPageEnd> topProjects = new ArrayList<>();
        for (Project project : projects) {
            topProjects.add(new ProjectTopPageEnd(project.getId(), project.getUrlKey(), project.getName(), project.getProjectStartAt(), project.getProjectEndAt()));
        }

        return ResponseEntity.ok(topProjects);
    }

    @GetMapping("/top/progress")
    public ResponseEntity<List<ProjectProgress>> getTopProjectsProgress() {
        List<Project> projects = projectService.getProgressProjects();
        List<ProjectProgress> topProjects = new ArrayList<>();
        for (Project project : projects) {
            topProjects.add(new ProjectProgress(project.getId(), project.getUrlKey(), project.getName(), project.getMainImgUrl(), project.getProjectStartAt(), project.getProjectEndAt(), project.getIntroduction()));
        }

        return ResponseEntity.ok(topProjects);
    }

    @GetMapping("/list")
    public ResponseEntity<ProjectPagination> getListPageProjectEnd(@RequestParam(defaultValue = "1") int page) {
        ProjectPagination pp = projectService.getProjectPagination(page);
        return ResponseEntity.ok(pp);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/new")
    public ResponseEntity<Project> insert(@ModelAttribute ProjectRegisterRequest projectRegisterRequest, UriComponentsBuilder uriComponentsBuilder) {
        Project createdProject = projectService.insert(projectRegisterRequest);
        URI location = uriComponentsBuilder.path("/api/projects/{id}")
                .buildAndExpand(createdProject.getId()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return ResponseEntity.created(location).body(createdProject);
    }

    // 書評投稿の禁止事項情報
    @GetMapping("/prohibitions/{id}")
    public ResponseEntity<ProjectProhibitionsInfo> getProhibitions(@PathVariable long id) {
        Project project =  projectService.findById(id);
        ProjectProhibitionsInfo  projectProhibitionsInfo = new ProjectProhibitionsInfo();
        projectProhibitionsInfo.setEnableVisibleBookTitle(project.isEnableVisibleBookTitle());
        return ResponseEntity.ok(projectProhibitionsInfo);
    }
}
