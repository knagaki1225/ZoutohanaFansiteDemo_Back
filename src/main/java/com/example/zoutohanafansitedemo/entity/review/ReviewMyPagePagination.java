package com.example.zoutohanafansitedemo.entity.review;

import com.example.zoutohanafansitedemo.entity.info.PaginationInfo;
import lombok.Data;

import java.util.List;

@Data
public class ReviewMyPagePagination {
    private PaginationInfo paginationInfo;
    private List<ReviewMyPage> reviewList;

    public ReviewMyPagePagination(PaginationInfo paginationInfo, List<ReviewMyPage> reviewList) {
        this.paginationInfo = paginationInfo;
        this.reviewList = reviewList;
    }
}
