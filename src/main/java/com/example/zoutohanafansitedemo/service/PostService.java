package com.example.zoutohanafansitedemo.service;

import com.example.zoutohanafansitedemo.entity.enums.PostCategory;
import com.example.zoutohanafansitedemo.entity.info.PaginationInfo;
import com.example.zoutohanafansitedemo.entity.info.PaginationView;
import com.example.zoutohanafansitedemo.entity.post.*;
import com.example.zoutohanafansitedemo.exception.InvalidCategoryException;
import com.example.zoutohanafansitedemo.exception.PostNotFoundException;
import com.example.zoutohanafansitedemo.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final PaginationService paginationService;


    public PostService(PostRepository postRepository, PaginationService paginationService) {
        this.postRepository = postRepository;
        this.paginationService = paginationService;
    }

    public List<Post> selectAll() {
        return postRepository.selectAll();
    }

    public Post findById(long id) {
        return postRepository.findById(id);
    }

    public List<PostTop> getTopLatest() {
        return postRepository.fetchTopLatest();
    }

    public List<PostView> getNew(){
        return postRepository.selectNew();
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

    public List<PostView> fetchListCategory(String category) {
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
        List<PostView> posts;
        if(category.equals("new")){
            posts = getNew();
        }else{
            String searchStr = category.toUpperCase();

            try{
                PostCategory.valueOf(searchStr);
            }catch(IllegalArgumentException e){
                throw new InvalidCategoryException("Invalid category");
            }

            posts = fetchListCategory(searchStr);
        }
        List<PostView> resultPosts = new ArrayList<>();

        PaginationView paginationView = paginationService.getPaginationView(page, posts.size(), 6);

        PaginationInfo paginationInfo = paginationView.getPaginationInfo();

        for(int i = paginationView.getStartNum(); i < paginationView.getEndNum(); i++){
            PostView post = posts.get(i);
            resultPosts.add(new PostView(post.getId(), post.getCategory(), post.getTitle(), post.getContent(), post.getPostedAt()));
        }

        return new PostPagination(paginationInfo, resultPosts);
    }

    public List<PostView> selectWithNeighbors(long id) {
        return postRepository.selectWithNeighbors(id);
    }
}
