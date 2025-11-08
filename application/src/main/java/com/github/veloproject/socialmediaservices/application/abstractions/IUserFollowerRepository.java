package com.github.veloproject.socialmediaservices.application.abstractions;

import com.github.veloproject.socialmediaservices.domain.entities.UserFollowerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserFollowerRepository {
    void save(UserFollowerEntity entity);
    Page<UserFollowerEntity> findAllByUserId(Integer userId, Pageable pageable);
    Page<UserFollowerEntity> findAllByFollowerId(Integer userId, Pageable pageable);
    void delete(Integer userId, Integer followerId);
}
