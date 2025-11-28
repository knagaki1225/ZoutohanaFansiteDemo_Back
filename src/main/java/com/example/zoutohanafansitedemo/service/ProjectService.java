package com.example.zoutohanafansitedemo.service;

import com.example.zoutohanafansitedemo.entity.info.PaginationInfo;
import com.example.zoutohanafansitedemo.entity.info.PaginationView;
import com.example.zoutohanafansitedemo.entity.project.Project;
import com.example.zoutohanafansitedemo.entity.project.ProjectList;
import com.example.zoutohanafansitedemo.entity.project.ProjectMyPage;
import com.example.zoutohanafansitedemo.entity.project.ProjectPagination;
import com.example.zoutohanafansitedemo.exception.InvalidPaginationException;
import com.example.zoutohanafansitedemo.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final PaginationService paginationService;

    public ProjectService(ProjectRepository projectRepository, PaginationService paginationService) {
        this.projectRepository = projectRepository;
        this.paginationService = paginationService;
    }

    public List<Project> getAll() {
        return projectRepository.selectAll();
    }

    public Project findById(long id) {
        return projectRepository.findById(id);
    }

    public List<Project> getFourEndProject(){
        return projectRepository.selectFourEndProjects();
    }

    public List<Project> getProgressProjects(){
        return projectRepository.selectProgressProjects();
    }

    public ProjectPagination getProjectPagination(int page){

        List<Project> projects = projectRepository.selectEndProjects();
        List<ProjectList> projectLists = new ArrayList<>();


        PaginationView paginationView = paginationService.getPaginationView(page, projects.size(), 5);

        PaginationInfo paginationInfo = paginationView.getPaginationInfo();

        for(int i = paginationView.getStartNum(); i < paginationView.getEndNum(); i++){
            Project project = projects.get(i);
            projectLists.add(new ProjectList(project.getId(), project.getUrlKey(), project.getName(), project.getProjectStartAt(), project.getProjectEndAt(), project.getIntroduction()));
        }

        return new ProjectPagination(paginationInfo, projectLists);
    }

    public Project insert(Project project) {
        projectRepository.insert(project);
        return project;
    }

    public List<ProjectMyPage> getProjectMyPage(){
        List<ProjectMyPage> projectMyPages = new ArrayList<>();
        List<Project> progressProjects = getProgressProjects();

        for(Project p:progressProjects) {
            ProjectMyPage projectMyPage = new ProjectMyPage();
            projectMyPage.setId(p.getId());
            projectMyPage.setName(p.getName());
            projectMyPage.setProjectStatus(p.getStatus());

            LocalDateTime today =  LocalDateTime.now();
            Integer finishDate = null;

            switch (p.getStatus()) {
                case BEFORE_SUBMISSION:
                    finishDate = (int) ChronoUnit.DAYS.between(today, p.getSubmissionStartAt()) + 1;
                    break;
                case DURING_SUBMISSION:
                    finishDate = (int) ChronoUnit.DAYS.between(today, p.getSubmissionEndAt()) + 1;
                    break;
                case FIRST_PHASE:
                    if(p.getVotingStartAt() != null){
                        finishDate = (int) ChronoUnit.DAYS.between(today, p.getVotingStartAt()) + 1;
                    }
                    break;
                case SECOND_PHASE_VOTING:
                    finishDate = (int) ChronoUnit.DAYS.between(today, p.getVotingEndAt()) + 1;
                    break;
            }
            projectMyPage.setLastDate(finishDate);

            projectMyPages.add(projectMyPage);
        }

        return projectMyPages;
    }
}
