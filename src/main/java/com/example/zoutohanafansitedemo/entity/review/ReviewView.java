package com.example.zoutohanafansitedemo.entity.review;

import com.example.zoutohanafansitedemo.entity.enums.UserGender;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewView {
    private long id;
    private long projectId;
    private long userId;
    private String userNickname;
    private String userAddress;
    private int userAgeGroup;
    private UserGender userGender;  // enums/UserGender
    private String userSelfIntroduction;
    private long bookIsbn;
    private String bookTitle;
    private String bookPublisher;
    private String bookAuthor;
    private String reviewTitle;
    private String reviewContent;
    private Integer voteCount;
    private LocalDateTime createdAt;
}
