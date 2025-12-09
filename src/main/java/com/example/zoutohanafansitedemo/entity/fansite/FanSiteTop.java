package com.example.zoutohanafansitedemo.entity.fansite;

import com.example.zoutohanafansitedemo.entity.post.PostTop;
import com.example.zoutohanafansitedemo.entity.project.ProjectProgress;
import lombok.Data;

import java.util.List;

@Data
public class FanSiteTop {
    List<PostTop> posts;
    List<ProjectProgress> projects;
}
