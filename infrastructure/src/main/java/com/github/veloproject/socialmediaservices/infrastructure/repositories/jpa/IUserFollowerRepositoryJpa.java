package com.github.veloproject.socialmediaservices.infrastructure.repositories.jpa;

import com.github.veloproject.socialmediaservices.domain.valueObjects.UserFollowerId;
import com.github.veloproject.socialmediaservices.infrastructure.tables.UserFollowersTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserFollowerRepositoryJpa extends JpaRepository<UserFollowersTable, UserFollowerId> {
    Page<UserFollowersTable> findAllByUserId(Integer userId, Pageable pageable);
    Page<UserFollowersTable> findAllByFollowerId(Integer followerId, Pageable pageable);

    void deleteByUserIdAndFollowerId(Integer userId, Integer followerId);
}
