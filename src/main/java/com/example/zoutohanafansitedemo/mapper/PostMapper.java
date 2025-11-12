package com.example.zoutohanafansitedemo.mapper;

import com.example.zoutohanafansitedemo.entity.Post;
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
}
