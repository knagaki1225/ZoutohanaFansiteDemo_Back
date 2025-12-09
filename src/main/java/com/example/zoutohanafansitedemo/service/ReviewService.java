package com.example.zoutohanafansitedemo.service;

import com.example.zoutohanafansitedemo.auth.CustomUserDetails;
import com.example.zoutohanafansitedemo.entity.info.PaginationInfo;
import com.example.zoutohanafansitedemo.entity.info.PaginationView;
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
    private final PaginationService paginationService;

    public ReviewService(ReviewRepository reviewRepository, ReviewMapper reviewMapper, PaginationService paginationService) {
        this.reviewRepository = reviewRepository;
        this.paginationService = paginationService;
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

    public Review insert(ReviewRegisterRequest insertReview, CustomUserDetails userDetails) {
        Review review = new Review();
        review.setProjectId(insertReview.getProjectId());
        review.setBookIsbn(insertReview.getBookIsbn());
        review.setBookTitle(insertReview.getBookTitle());
        review.setBookPublisher(insertReview.getBookPublisher());
        review.setBookAuthor(insertReview.getBookAuthor());
        review.setReviewTitle(insertReview.getReviewTitle());
        review.setReviewContent(insertReview.getReviewContent());
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
        List<Review> reviews = reviewRepository.selectByUserIdOrderByDesc(userId);
        List<ReviewMyPage> reviewList = new ArrayList<>();

        if(reviews.isEmpty()){
            return new ReviewMyPagePagination(new PaginationInfo(0, 0), reviewList);
        }

        PaginationView paginationView = paginationService.getPaginationView(page, reviews.size(), 3);

        PaginationInfo paginationInfo = paginationView.getPaginationInfo();
        for(int i = paginationView.getStartNum(); i < paginationView.getEndNum(); i++){
            Review review = reviews.get(i);
            ReviewMyPage reviewMyPage = new ReviewMyPage(
                    review.getId(), review.getBookTitle(), review.getBookPublisher(), review.getBookAuthor(), review.getReviewTitle(), review.getReviewContent(), review.getVoteCount(), review.getProjectId(),review.getProjectName()
            );
            reviewList.add(reviewMyPage);
        }

        return new ReviewMyPagePagination(paginationInfo, reviewList);
    }
}
