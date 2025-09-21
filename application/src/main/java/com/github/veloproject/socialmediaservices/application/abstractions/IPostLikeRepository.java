package com.github.veloproject.socialmediaservices.application.abstractions;

import com.github.veloproject.socialmediaservices.domain.entities.PostLikeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPostLikeRepository {
    void like(PostLikeEntity entity);
    void unlike(Integer postId, Integer userId);
    boolean hasLiked(Integer postId, Integer userId);
    long countLikes(Integer postId);
    Page<PostLikeEntity> findLikesByPost(Integer postId, Pageable pageable);
}
