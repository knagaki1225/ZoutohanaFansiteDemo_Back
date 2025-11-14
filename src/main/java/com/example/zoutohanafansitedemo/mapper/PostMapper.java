package com.example.zoutohanafansitedemo.mapper;

import com.example.zoutohanafansitedemo.entity.post.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostMapper {
//    ==========ここからデバッグ用(削除済み・非表示も返す)==========
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
    """)
    List<Post> selectAll();

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
        WHERE id = #{id}
    """)
    Post findById(long id);

//    ==========ここまでデバッグ用(削除済み・非表示も返す)==========

    @Select("""
        SELECT
            id,
            category,
            title,
            posted_at
        FROM posts
        WHERE is_delete = false
            AND status = 3
        ORDER BY posted_at DESC
        LIMIT 0, 4
    """)
    List<PostTop> fetchTopLatest();

    @Select("""
        SELECT
            id,
            category,
            title,
            posted_at
        FROM posts
        WHERE is_delete = false
            AND status = 3
            AND category = #{category}
        ORDER BY posted_at DESC
        LIMIT 0, 4
    """)
    List<PostTop> fetchTopCategory(int category);

    @Select("""
        SELECT
            id,
            title,
            content,
            posted_at
        FROM posts
        WHERE is_delete = false
            AND status = 3
            AND category = #{category}
        ORDER BY posted_at DESC
    """)
    List<PostList> fetchListCategory(int category);

    @Select("""
        SELECT
            id,
            category,
            title,
            content,
            posted_at
        FROM posts
        WHERE is_delete = false
            AND status = 3
            AND id = #{id}
    """)
    PostView selectById(long id);
}
