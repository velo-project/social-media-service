package com.github.veloproject.socialmediaservices.infrastructure.repositories.jpa;

import com.github.veloproject.socialmediaservices.infrastructure.tables.CommentsTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepositoryJpa extends JpaRepository<CommentsTable, Integer> {
    Page<CommentsTable> findByPostId(Integer postId, Pageable pageable);
    long countByPostId(Integer postId);
}
