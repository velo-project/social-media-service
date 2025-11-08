package com.github.veloproject.socialmediaservices.infrastructure.repositories.jpa;

import com.github.veloproject.socialmediaservices.domain.entities.HashtagEntity;
import com.github.veloproject.socialmediaservices.infrastructure.tables.HashtagsTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IHashtagRepositoryJpa extends JpaRepository<HashtagsTable, Integer> {
    boolean existsByTag(String tag);

    Optional<HashtagEntity> findByTag(String tag);
}
