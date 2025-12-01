package com.example.zoutohanafansitedemo.controller;

import com.example.zoutohanafansitedemo.auth.CustomUserDetails;
import com.example.zoutohanafansitedemo.entity.info.MyPageInfo;
import com.example.zoutohanafansitedemo.entity.project.Project;
import com.example.zoutohanafansitedemo.entity.project.ProjectMyPage;
import com.example.zoutohanafansitedemo.entity.review.ReviewMyPagePagination;
import com.example.zoutohanafansitedemo.entity.user.UserMyPage;
import com.example.zoutohanafansitedemo.service.ProjectService;
import com.example.zoutohanafansitedemo.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/mypage")
@PreAuthorize("hasRole('USER')")
public class MyPageController {
    private final ProjectService projectService;
    private final ReviewService reviewService;

    public MyPageController(ProjectService projectService, ReviewService reviewService) {
        this.projectService = projectService;
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<MyPageInfo> getMyPageInfo(
            @RequestParam(defaultValue = "1") int page, @AuthenticationPrincipal CustomUserDetails user) {
        UserMyPage userMyPage = new UserMyPage(user.getUsername(), user.getSelfIntroduction(), user.getNickname());
        List<ProjectMyPage> projects = projectService.getProjectMyPage();
        ReviewMyPagePagination reviews = reviewService.getReviewMyPage(page, user.getId());

        MyPageInfo myPageInfo = new MyPageInfo(userMyPage, projects, reviews);

        return ResponseEntity.ok(myPageInfo);
    }
}
