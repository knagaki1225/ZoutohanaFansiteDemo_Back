package com.example.zoutohanafansitedemo.entity.post;

import com.example.zoutohanafansitedemo.entity.info.PaginationInfo;
import lombok.Data;

import java.util.List;

@Data
public class PostPagination {
    private PaginationInfo paginationInfo;
    private List<PostList> postLists;

    public PostPagination(PaginationInfo paginationInfo, List<PostList> postLists) {
        this.paginationInfo = paginationInfo;
        this.postLists = postLists;
    }
}
