package com.example.zoutohanafansitedemo.service;

import com.example.zoutohanafansitedemo.entity.info.PaginationInfo;
import com.example.zoutohanafansitedemo.entity.post.*;
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
        return postRepository.fetchTopCategory(category);
    }

    public List<PostList> fetchListCategory(String category) {
        return postRepository.fetchListCategory(category);
    }

    public PostView selectById(long id) {
        return postRepository.selectById(id);
    }

    public PostPagination getPostPagination(String category, int page) {
        List<PostList> posts = fetchListCategory(category);
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
