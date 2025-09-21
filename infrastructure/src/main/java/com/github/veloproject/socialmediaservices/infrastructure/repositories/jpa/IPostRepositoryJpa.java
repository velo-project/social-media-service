package com.github.veloproject.socialmediaservices.infrastructure.repositories.jpa;

import com.github.veloproject.socialmediaservices.infrastructure.tables.PostsTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostRepositoryJpa extends JpaRepository<PostsTable, Integer> {
    Page<PostsTable> findByPostedBy(Integer postedBy, Pageable pageable);
}
