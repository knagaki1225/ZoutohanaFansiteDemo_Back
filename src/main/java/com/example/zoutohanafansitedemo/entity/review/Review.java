package com.example.zoutohanafansitedemo.entity.review;

import com.example.zoutohanafansitedemo.entity.enums.UserGender;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Review {
    private long id;

    @NotNull
    private Long projectId;

    @NotNull
    private Long userId;

    private String userNickname;
    private String userAddress;
    private int userAgeGroup;
    private UserGender userGender;  // enums/UserGender
    private String userSelfIntroduction;

    @NotNull
    private Long bookIsbn;

    private String bookTitle;
    private String bookPublisher;
    private String bookAuthor;

    @NotBlank
    private String reviewTitle;

    @NotBlank
    private String reviewContent;

    private String reviewContentEdited;
    private int voteCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isDelete;
}
