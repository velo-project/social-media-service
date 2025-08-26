package com.github.veloproject.socialmediaservices.application.abstractions;

import com.github.veloproject.socialmediaservices.domain.entities.PostEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostRepository {
    Integer save(PostEntity user);
}
