package com.example.zoutohanafansitedemo.entity.review;

import com.example.zoutohanafansitedemo.entity.enums.UserGender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewRegisterRequest {
    private Long projectId;
    private Long bookIsbn;
    private String bookTitle;
    private String bookPublisher;
    private String bookAuthor;
    private String reviewTitle;
    private String reviewContent;
}
