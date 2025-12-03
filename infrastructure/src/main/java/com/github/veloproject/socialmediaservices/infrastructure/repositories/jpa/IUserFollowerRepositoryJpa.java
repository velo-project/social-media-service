package com.github.veloproject.socialmediaservices.infrastructure.repositories.jpa;

import com.github.veloproject.socialmediaservices.domain.valueObjects.UserFollowerId;
import com.github.veloproject.socialmediaservices.infrastructure.tables.UserFollowersTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserFollowerRepositoryJpa extends JpaRepository<UserFollowersTable, UserFollowerId> {
    Page<UserFollowersTable> findAllByUserId(Integer userId, Pageable pageable);
    Page<UserFollowersTable> findAllByFollowerId(Integer followerId, Pageable pageable);
    @Query(value = """
    SELECT
        A.id_user
    FROM
        tb_user_followers A
    INNER JOIN
        tb_user_followers B
        ON A.id_user = B.id_follower
        AND A.id_follower = B.id_user
    WHERE
        A.id_follower = :userId
    """, nativeQuery = true)
    List<Integer> getFriends(@Param("userId") Integer userId);

    void deleteByUserIdAndFollowerId(Integer userId, Integer followerId);
}
