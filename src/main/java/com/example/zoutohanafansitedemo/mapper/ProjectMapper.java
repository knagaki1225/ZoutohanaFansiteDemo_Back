package com.example.zoutohanafansitedemo.mapper;

import com.example.zoutohanafansitedemo.entity.project.Project;
import com.example.zoutohanafansitedemo.entity.review.Review;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProjectMapper {
    @Select("""
            SELECT *
            FROM Projects
            """)
    List<Project> selectAll();

    @Select("""
            SELECT *
            FROM Projects
            WHERE id = #{id}
            """)
    Project findById(long id);

    @Select("""
            SELECT *
            FROM Projects
            WHERE deleted = FALSE
                AND project_end_at < NOW()
                AND published = TRUE
            ORDER BY project_end_at DESC
            LIMIT 4
            """)
    List<Project> selectFourEndProjects();

    @Select("""
            SELECT *
            FROM Projects
            WHERE deleted = FALSE
                AND NOW() BETWEEN project_start_at AND project_end_at;
            """)
    List<Project> selectProgressProjects();

    @Select("""
            SELECT *
            FROM Projects
            WHERE deleted = FALSE
                AND project_end_at < NOW()
                AND published = TRUE
            ORDER BY project_end_at DESC
            """)
    List<Project> selectEndProjects();

    @Insert("""
            INSERT INTO Projects (
                name,
                url_key,
                introduction,
                main_img_url,
                theme_color,
                status,
                enablevisible_book_title,
                enablevisible_review_title,
                enablevisible_user_info,
                published,
                project_start_at,
                project_end_at,
                submission_start_at,
                submission_end_at,
                voting_start_at,
                voting_end_at
            ) VALUES (
                #{name},
                #{urlKey},
                #{introduction},
                #{mainImgUrl},
                #{themeColor},
                #{status},
                #{isVisibleBookTitle},
                #{isVisibleReviewTitle},
                #{isVisibleUserInfo},
                #{isPublic},
                #{projectStartAt},
                #{projectEndAt},
                #{submissionStartAt},
                #{submissionEndAt},
                #{votingStartAt},
                #{votingEndAt}
            )
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id,createdAt,updatedAt", keyColumn = "id,created_at,updated_at")
    int insert(Project project);

    @Select("""
            SELECT * 
            FROM projects
            WHERE url_key = #{urlKey}
            """)
    Project findByUrlKey(String urlKey);
}
