package com.example.zoutohanafansitedemo.entity.project;

import com.example.zoutohanafansitedemo.entity.enums.ProjectStatus;
import com.example.zoutohanafansitedemo.entity.enums.ThemeColor;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
public class ProjectRegisterRequest {
    private String name;
    private String urlKey;
    private String introduction;
    private MultipartFile mainImg;
    private ThemeColor themeColor;  // enums/ThemeColor
    private boolean enableVisibleBookTitle;
    private boolean enableVisibleReviewTitle;
    private boolean enableVisibleUserInfo;
    private boolean published;
    private LocalDateTime projectStartAt;
    private LocalDateTime projectEndAt;
    private LocalDateTime submissionStartAt;
    private LocalDateTime submissionEndAt;
    private LocalDateTime votingStartAt;
    private LocalDateTime votingEndAt;
}
