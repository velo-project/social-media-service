package com.github.veloproject.socialmediaservices.infrastructure.repositories.jpa;

import com.github.veloproject.socialmediaservices.domain.entities.PostEntity;
import com.github.veloproject.socialmediaservices.infrastructure.tables.PostsTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostRepositoryJpa extends JpaRepository<PostsTable, Integer> {
    Page<PostsTable> findByPostedBy(Integer postedBy, Pageable pageable);

    @Query(value = """
WITH recent_hashtags AS (
    SELECT DISTINCT ON (ph.id_hashtag) ph.id_hashtag
    FROM tb_post_likes pl
    JOIN tb_post_hashtags ph ON ph.id_post = pl.id_post
    WHERE pl.liked_by = :userId
      AND pl.liked_at >= NOW() - INTERVAL '5 days'
    ORDER BY ph.id_hashtag, pl.liked_at DESC
    LIMIT 15
),
posts_by_interest AS (
    SELECT DISTINCT p.*
    FROM tb_posts p
    JOIN tb_post_hashtags ph ON ph.id_post = p.id_post
    WHERE ph.id_hashtag IN (SELECT id_hashtag FROM recent_hashtags)
)
SELECT p.*
FROM posts_by_interest p
UNION ALL
SELECT p.*
FROM tb_posts p
WHERE p.id_post NOT IN (SELECT id_post FROM posts_by_interest)
ORDER BY posted_at DESC
LIMIT 50;
        """, nativeQuery = true)
    List<PostsTable> findRecommendedFeed(@Param("userId") Integer userId);

    boolean existsByIdAndIsDeletedFalse(Integer id);

    Page<PostsTable> findByPostedByAndIsDeletedFalse(Integer postedBy, Pageable pageable);
}
