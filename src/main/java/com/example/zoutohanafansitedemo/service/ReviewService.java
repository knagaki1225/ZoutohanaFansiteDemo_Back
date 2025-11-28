package com.example.zoutohanafansitedemo.service;

import com.example.zoutohanafansitedemo.auth.CustomUserDetails;
import com.example.zoutohanafansitedemo.entity.info.PaginationInfo;
import com.example.zoutohanafansitedemo.entity.review.*;
import com.example.zoutohanafansitedemo.mapper.ReviewMapper;
import com.example.zoutohanafansitedemo.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
    }

    private int toAgeGroup(int birthYear){
        int currentYear = LocalDate.now().getYear();
        int age = currentYear - birthYear;
        return (age / 10) * 10;
    }

    public List<Review> selectAll() {
        return reviewRepository.selectAll();
    }

    public Review findById(long id) {
        return reviewRepository.findById(id);
    }

    public List<ReviewView> selectByProjectId(long projectId) {
        return reviewRepository.selectByProjectId(projectId);
    }

    public List<ReviewView> selectRandomByProjectId(long projectId) {
        return reviewRepository.selectRandomByProjectId(projectId);
    }

    public List<Review> selectByUserId(long userId) {
        return reviewRepository.selectByUserId(userId);
    }

    public Review insert(Review review, CustomUserDetails userDetails) {
        review.setUserId(userDetails.getId());
        review.setUserNickname(userDetails.getNickname());
        review.setUserAddress(userDetails.getAddress());
        review.setUserAgeGroup(toAgeGroup(userDetails.getBirthYear()));
        review.setUserGender(userDetails.getGender());
        review.setUserSelfIntroduction(userDetails.getSelfIntroduction());

        reviewRepository.insert(review);
        return review;
    }

    public ReviewMyPagePagination getReviewMyPage(int page, long userId){
        List<Review> reviews = reviewRepository.selectByUserId(userId);
        List<ReviewMyPage> reviewList = new ArrayList<>();

        int size = reviews.size();
        int pageSize = size / 3;
        if(size % 3 != 0){
            pageSize++;
        }

        while (page > pageSize){
            page--;
        }

        int count = (page - 1) * 3;
        int num = reviews.size();
        if(count + 2 <= num){
            num = count + 3;
        }

        PaginationInfo paginationInfo = new PaginationInfo(page, pageSize);

        for(int i = count; i < num; i++){
            Review review = reviews.get(i);
            ReviewMyPage reviewMyPage = new ReviewMyPage(
                    review.getId(), review.getBookTitle(), review.getBookPublisher(), review.getBookAuthor(), review.getReviewTitle(), review.getReviewContent(), review.getVoteCount()
            );
            reviewList.add(reviewMyPage);
        }

        return new ReviewMyPagePagination(paginationInfo, reviewList);
    }
}
