package com.github.veloproject.socialmediaservices.application.abstractions;

import com.github.veloproject.socialmediaservices.application.dto.CommunityDto;
import com.github.veloproject.socialmediaservices.domain.entities.CommunityEntity;

import java.util.List;
import java.util.Optional;

public interface ICommunityRepository {
    Optional<CommunityEntity> findById(Integer id);
    boolean existsById(Integer id);
    Integer save(CommunityEntity communityEntity);
    void deleteById(Integer id);
    List<CommunityDto> findSimilarByEmbedding(float[] embedding, float threshold);
}
