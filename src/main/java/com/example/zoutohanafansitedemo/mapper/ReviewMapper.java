package com.example.zoutohanafansitedemo.mapper;

import com.example.zoutohanafansitedemo.entity.review.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReviewMapper {
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
            review_content_edited,
            vote_count,
            created_at,
            updated_at,
            is_delete
        FROM Reviews;
    """)
    List<Review> findAll();
}
