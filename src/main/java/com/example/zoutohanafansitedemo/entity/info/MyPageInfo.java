package com.example.zoutohanafansitedemo.entity.info;

import com.example.zoutohanafansitedemo.entity.project.ProjectMyPage;
import com.example.zoutohanafansitedemo.entity.review.ReviewMyPagePagination;
import com.example.zoutohanafansitedemo.entity.user.UserMyPage;
import lombok.Data;

import java.util.List;

@Data
public class MyPageInfo {
    private UserMyPage myInfo;
    private List<ProjectMyPage> projects;
    private ReviewMyPagePagination reviews;

    public MyPageInfo(UserMyPage myInfo, List<ProjectMyPage> projects, ReviewMyPagePagination reviews) {
        this.myInfo = myInfo;
        this.projects = projects;
        this.reviews = reviews;
    }
}
