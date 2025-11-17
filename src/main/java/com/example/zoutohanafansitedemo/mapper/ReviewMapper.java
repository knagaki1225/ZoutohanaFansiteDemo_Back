package com.example.zoutohanafansitedemo.mapper;

import com.example.zoutohanafansitedemo.entity.review.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReviewMapper {
//    ==========ここからデバッグ用(削除済みも返す)==========
    @Select("""
        SELECT *
        FROM Reviews;
    """)
    List<Review> selectAll();

    @Select("""
        SELECT *
        FROM Reviews
        WHERE id = #{id};
    """)
    Review findById(long id);
//    ==========ここまでデバッグ用(削除済みも返す)==========


//    「企画ページ第二次審査」で書評を選択した際のデザインが更新されたら更新
    @Select("""
        SELECT
            id,
            project_id,
            user_id,
            user_nickname,
            user_address,
            user_age_group,
            user_gender,
            user_self_introduction,
            book_isbn,
            book_title,
            book_publisher,
            book_author,
            review_title,
            review_content,
            vote_count,
            created_at
        FROM Reviews
        WHERE is_delete = FALSE
            AND project_id = #{projectId};
    """)
    List<ReviewView> selectByProjectId(long projectId);

    @Select("""
        SELECT
            id,
            project_id,
            user_id,
            user_nickname,
            user_address,
            user_age_group,
            user_gender,
            user_self_introduction,
            book_isbn,
            book_title,
            book_publisher,
            book_author,
            review_title,
            review_content,
            vote_count,
            created_at
        FROM Reviews
        WHERE is_delete = FALSE
            AND project_id = #{projectId}
        ORDER BY RAND()
        LIMIT 18;
    """)
    List<ReviewView> selectRandomByProjectId(long projectId);

    @Select("""
        SELECT
            id,
            project_id,
            user_id,
            book_isbn,
            book_title,
            book_publisher,
            book_author,
            review_title,
            review_content,
            vote_count,
            created_at
        FROM Reviews
        WHERE is_delete = FALSE
            AND user_id = #{userId};
    """)
    List<ReviewMypage> selectByUserId(long userId);

}
