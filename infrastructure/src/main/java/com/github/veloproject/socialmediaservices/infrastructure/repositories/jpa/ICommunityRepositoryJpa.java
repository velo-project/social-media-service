package com.github.veloproject.socialmediaservices.infrastructure.repositories.jpa;

import com.github.veloproject.socialmediaservices.infrastructure.tables.CommunitiesTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommunityRepositoryJpa extends JpaRepository<CommunitiesTable, Integer> {
    @Query("""
        SELECT c FROM CommunitiesTable c
        WHERE cosine_distance(c.embeddings, :embedding) < :threshold
        ORDER BY cosine_distance(c.embeddings, :embedding) ASC
    """)
    List<CommunitiesTable> findSimilarByEmbedding(@Param("embedding") float[] embedding,
                                                  @Param("threshold") float threshold);
}
