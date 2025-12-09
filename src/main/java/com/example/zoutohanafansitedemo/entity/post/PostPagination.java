package com.example.zoutohanafansitedemo.entity.post;

import com.example.zoutohanafansitedemo.entity.info.PaginationInfo;
import lombok.Data;

import java.util.List;

@Data
public class PostPagination {
    private PaginationInfo paginationInfo;
    private List<PostView> postViews;

    public PostPagination(PaginationInfo paginationInfo, List<PostView> postViews) {
        this.paginationInfo = paginationInfo;
        this.postViews = postViews;
    }
}
