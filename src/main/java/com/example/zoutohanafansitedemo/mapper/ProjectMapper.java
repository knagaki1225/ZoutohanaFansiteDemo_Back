package com.example.zoutohanafansitedemo.mapper;

import com.example.zoutohanafansitedemo.entity.project.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProjectMapper {
    @Select("""
            SELECT *
            FROM Projects
            WHERE is_delete = FALSE
              AND end_at < NOW()
              AND is_public = TRUE
            ORDER BY end_at DESC
            LIMIT 4
            """)
    List<Project> selectFourEndProjects();

    @Select("""
            SELECT *
            FROM Projects
            WHERE is_delete = FALSE
              AND NOW() BETWEEN start_at AND end_at;
            """)
    List<Project> selectProgressProjects();

    @Select("""
            SELECT *
            FROM Projects
            WHERE is_delete = FALSE
              AND end_at < NOW()
              AND is_public = TRUE
            ORDER BY end_at DESC
            """)
    List<Project> selectEndProjects();
}
