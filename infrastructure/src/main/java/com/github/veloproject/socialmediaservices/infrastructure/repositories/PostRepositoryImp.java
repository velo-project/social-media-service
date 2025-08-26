package com.github.veloproject.socialmediaservices.infrastructure.repositories;

import com.github.veloproject.socialmediaservices.application.abstractions.IPostRepository;
import com.github.veloproject.socialmediaservices.domain.entities.PostEntity;
import com.github.veloproject.socialmediaservices.infrastructure.mappers.PostMapper;
import com.github.veloproject.socialmediaservices.infrastructure.repositories.jpa.IPostRepositoryJpa;
import org.springframework.stereotype.Repository;

@Repository
public class PostRepositoryImp implements IPostRepository {
    private final IPostRepositoryJpa jpa;

    public PostRepositoryImp(IPostRepositoryJpa jpa) {
        this.jpa = jpa;
    }

    @Override
    public Integer save(PostEntity entity) {
        var table = PostMapper.toPersistence(entity);

        return jpa
                .save(table)
                .getId();
    }
}
