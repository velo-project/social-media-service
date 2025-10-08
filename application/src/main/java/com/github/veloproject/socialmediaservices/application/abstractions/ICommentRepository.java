package com.github.veloproject.socialmediaservices.application.abstractions;

import com.github.veloproject.socialmediaservices.domain.entities.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICommentRepository {
    Integer save(CommentEntity comment);
    void deleteById(Integer commentId);
    Optional<CommentEntity> findById(Integer commentId);
    List<CommentEntity> findByPostId(Integer postId, Pageable pageable);
    long countByPostId(Integer postId);
}
