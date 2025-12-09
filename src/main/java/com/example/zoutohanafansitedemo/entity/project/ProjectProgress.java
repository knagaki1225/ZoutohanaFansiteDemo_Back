package com.example.zoutohanafansitedemo.entity.project;

import com.example.zoutohanafansitedemo.entity.enums.ThemeColor;
import lombok.Data;
import org.springframework.ui.context.Theme;

import java.time.LocalDateTime;

@Data
public class ProjectProgress {
    long id;
    private String urlKey;
    private String name;
    private String mainImgUrl;
    private String themeColor;
    private LocalDateTime projectStartAt;
    private LocalDateTime projectEndAt;
    private String introduction;

    public ProjectProgress(long id, String urlKey, String name, String mainImgUrl, ThemeColor themeColor, LocalDateTime projectStartAt, LocalDateTime projectEndAt, String introduction) {
        this.id = id;
        this.urlKey = urlKey;
        this.name = name;
        this.mainImgUrl = mainImgUrl;
        this.themeColor = themeColor.getDbValue();
        this.projectStartAt = projectStartAt;
        this.projectEndAt = projectEndAt;
        this.introduction = introduction;
    }
}
