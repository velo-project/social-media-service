package com.github.veloproject.socialmediaservices.application.abstractions;

import com.github.veloproject.socialmediaservices.domain.entities.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IPostRepository {
    Integer save(PostEntity user);
    void deleteById(Integer postId);
    Optional<PostEntity> findById(Integer id);
    boolean existsById(Integer postId);
    Page<PostEntity> findPageByUserId(Integer userId, Pageable pageable);
}
