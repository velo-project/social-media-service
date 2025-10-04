package com.github.veloproject.socialmediaservices.infrastructure.repositories;

import com.github.veloproject.socialmediaservices.application.abstractions.IPostRepository;
import com.github.veloproject.socialmediaservices.domain.entities.PostEntity;
import com.github.veloproject.socialmediaservices.infrastructure.mappers.PostMapper;
import com.github.veloproject.socialmediaservices.infrastructure.repositories.jpa.IPostRepositoryJpa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PostRepositoryImp implements IPostRepository {
    private final IPostRepositoryJpa jpa;

    public PostRepositoryImp(IPostRepositoryJpa jpa) {
        this.jpa = jpa;
    }

    @Override
    public PostEntity save(PostEntity entity) {
        var table = PostMapper
                .toPersistence(entity);

        return PostMapper
                .toDomain(jpa.save(table));
    }

    @Override
    public void deleteById(Integer postId) {
        jpa.deleteById(postId);
    }

    @Override
    public Optional<PostEntity> findById(Integer id) {
        var post = jpa.findById(id);
        return post
                .map(PostMapper::toDomain);
    }

    @Override
    public boolean existsById(Integer postId) {
        return jpa.existsById(postId);
    }

    @Override
    public Page<PostEntity> findPageByUserId(Integer userId, Pageable pageable) {
        return jpa
                .findByPostedBy(userId, pageable)
                .map(PostMapper::toDomain);
    }

    @Override
    public List<PostEntity> findRecommendedFeed(Integer userId) {
        return jpa.findRecommendedFeed(userId)
                .stream()
                .map(PostMapper::toDomain)
                .toList();
    }
}
