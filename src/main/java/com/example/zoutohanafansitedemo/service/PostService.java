package com.example.zoutohanafansitedemo.service;

import com.example.zoutohanafansitedemo.entity.enums.PostCategory;
import com.example.zoutohanafansitedemo.entity.info.PaginationInfo;
import com.example.zoutohanafansitedemo.entity.post.*;
import com.example.zoutohanafansitedemo.exception.InvalidCategoryException;
import com.example.zoutohanafansitedemo.exception.InvalidPaginationException;
import com.example.zoutohanafansitedemo.exception.PostNotFoundException;
import com.example.zoutohanafansitedemo.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;


    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> selectAll() {
        return postRepository.selectAll();
    }

    public Post findById(long id) {
        return postRepository.findById(id);
    }

    public List<PostTop> fetchTopLatest() {
        return postRepository.fetchTopLatest();
    }

    public List<PostTop> fetchTopCategory(String category) {
        String searchStr = category.toUpperCase();

        try{
            PostCategory.valueOf(searchStr);
        }catch(IllegalArgumentException e){
            throw new InvalidCategoryException("Invalid category");
        }


        List<PostTop> returnList = postRepository.fetchTopCategory(searchStr);
        return returnList;
    }

    public List<PostList> fetchListCategory(String category) {
        return postRepository.fetchListCategory(category);
    }

    public PostView selectById(long id) {
        PostView postView = postRepository.selectById(id);

        if(postView == null){
            throw new PostNotFoundException("Post not found");
        }

        return postView;
    }

    public PostPagination getPostPagination(String category, int page) {
        String searchStr = category.toUpperCase();

        try{
            PostCategory.valueOf(searchStr);
        }catch(IllegalArgumentException e){
            throw new InvalidCategoryException("Invalid category");
        }

        if(page < 1){
            throw new InvalidPaginationException("Invalid page number");
        }


        List<PostList> posts = fetchListCategory(searchStr);
        List<PostList> resultPosts = new ArrayList<>();

        int size = posts.size();
        int pageSize = size / 5;
        if(size % 5 != 0){
            pageSize++;
        }

        while(page > pageSize){
            page--;
        }

        // 全件のcount番目からnum番目まで取得する
        // 3ページまでしかないのに4ページ目をリクエストされた場合は3ページ目に自動的に変える
        int count = (page - 1) * 5;
        int num = posts.size();
        if(count + 4 <= num){
            num = count + 5;
        }

        PaginationInfo paginationInfo = new PaginationInfo(page, pageSize);

        for(int i = count; i < num; i++){
            PostList post = posts.get(i);
            resultPosts.add(new PostList(post.getId(), post.getTitle(), post.getContent(), post.getPostedAt()));
        }

        return new PostPagination(paginationInfo, resultPosts);
    }
}
