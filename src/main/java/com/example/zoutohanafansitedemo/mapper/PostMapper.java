package com.example.zoutohanafansitedemo.mapper;

import com.example.zoutohanafansitedemo.entity.post.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostMapper {
    @Select("""
        SELECT
            id,
            admin_id,
            category,
            title,
            content,
            posted_at,
            status,
            created_at,
            updated_at
        FROM posts
        WHERE is_delete = false
    """)
    List<Post> findAll();

    @Select("""
        SELECT
            id,
            admin_id,
            category,
            title,
            content,
            posted_at,
            status,
            created_at,
            updated_at
        FROM posts
        WHERE is_delete = false
            AND id = #{id}
    """)
    Post findById(Integer id);

    @Select("""
        SELECT
            id,
            category,
            title,
            posted_at
        FROM posts
        WHERE is_delete = false
        ORDER BY posted_at DESC
        LIMIT 0, 4
    """)
    List<PostTop> fetchTopLatest();
}
