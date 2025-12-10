package com.example.zoutohanafansitedemo.mapper;

import com.example.zoutohanafansitedemo.entity.post.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostMapper {
//    ==========ここからデバッグ用(削除済み・非表示も返す)==========
    @Select("""
        SELECT *
        FROM posts
    """)
    List<Post> selectAll();

    @Select("""
        SELECT *
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
        WHERE deleted = false
            AND status = 'PUBLIC'
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
        WHERE deleted = false
            AND status = 'PUBLIC'
            AND category = #{category}
        ORDER BY posted_at DESC
        LIMIT 0, 4
    """)
    List<PostTop> fetchTopCategory(String category);

    @Select("""
        SELECT
            id,
            category,
            title,
            content,
            posted_at
        FROM posts
        WHERE deleted = false
            AND status = 'PUBLIC'
            AND category = #{category}
            AND posted_at < NOW()
        ORDER BY posted_at DESC
    """)
    List<PostView> fetchListCategory(String category);

    @Select("""
        SELECT
            id,
            category,
            title,
            content,
            posted_at
        FROM posts
        WHERE deleted = false
            AND status = 'PUBLIC'
            AND id = #{id}
    """)
    PostView selectById(long id);

    @Select("""
        SELECT 
            id,
            category,
            title,
            content,
            posted_at
        FROM Posts
        WHERE deleted = FALSE
            AND status = 'PUBLIC'
            AND posted_at < NOW()
        ORDER BY created_at DESC;
    """)
    List<PostView> selectNew();

    @Select("""
        WITH sorted AS (
            SELECT
                p.*,
                ROW_NUMBER() OVER (
                    PARTITION BY p.category
                    ORDER BY p.posted_at
                ) AS rn
            FROM Posts p
            WHERE
                p.deleted = FALSE
                AND p.status = 'PUBLIC'
                AND p.category = (
                    SELECT category
                    FROM Posts
                    WHERE deleted = false
                        AND status = 'PUBLIC'
                        AND id = #{id}
                )
        )

        SELECT
            id,
            category,
            title,
            content,
            posted_at
        FROM sorted
        WHERE rn IN (
            (SELECT rn FROM sorted WHERE id = #{id}) - 1,
            (SELECT rn FROM sorted WHERE id = #{id}),
            (SELECT rn FROM sorted WHERE id = #{id}) + 1
        )
        ORDER BY rn;
    """)
    List<PostView> selectWithNeighbors(long id);
    //  [WITH ~ の方] 指定したお知らせと同カテゴリー・公開中のお知らせだけをpostedAt順に並べたsortedという仮テーブルを作り、その順番をrnとして持たせる
    //  [SELECT ~ の方] 指定したお知らせの一つ前の投稿・指定したお知らせ・指定したお知らせの一つ後を選択して返す
}
