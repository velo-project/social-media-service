package com.github.veloproject.socialmediaservices.infrastructure.repositories.jpa;

import aj.org.objectweb.asm.commons.Remapper;
import com.github.veloproject.socialmediaservices.domain.valueObjects.PostLikeId;
import com.github.veloproject.socialmediaservices.infrastructure.tables.PostLikesTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostLikeRepositoryJpa extends JpaRepository<PostLikesTable, PostLikeId> {
    long countByPostId(Integer postId);
    boolean existsByPostIdAndLikedBy(Integer postId, Integer likedBy);
    void deleteByPostIdAndLikedBy(Integer postId, Integer likedBy);

    Page<PostLikesTable> findByPostId(Integer postId, Pageable pageable);
}
