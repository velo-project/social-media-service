package com.github.veloproject.socialmediaservices.application.abstractions;

import com.github.veloproject.socialmediaservices.domain.entities.PostEntity;

import java.util.List;
import java.util.Optional;

public interface IPostRepository {
    Integer save(PostEntity user);
    void deleteById(Integer postId);
    Optional<PostEntity> findById(Integer id);
    boolean existsById(Integer postId);
    List<PostEntity> findAllByUserId(Integer userId);
}
