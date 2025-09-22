package com.github.veloproject.socialmediaservices.infrastructure.repositories;

import com.github.veloproject.socialmediaservices.application.abstractions.IPostLikeRepository;
import com.github.veloproject.socialmediaservices.domain.entities.PostLikeEntity;
import com.github.veloproject.socialmediaservices.infrastructure.mappers.PostLikeMapper;
import com.github.veloproject.socialmediaservices.infrastructure.repositories.jpa.IPostLikeRepositoryJpa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class PostLikeRepositoryImp implements IPostLikeRepository {
    private final IPostLikeRepositoryJpa jpa;

    public PostLikeRepositoryImp(IPostLikeRepositoryJpa jpa) {
        this.jpa = jpa;
    }

    @Override
    public void save(PostLikeEntity entity) {
        var table = PostLikeMapper.toPersistence(entity);
        jpa.save(table);
    }

    @Override
    public void delete(Integer postId, Integer userId) {
        jpa.deleteByPostIdAndLikedBy(postId, userId);
    }

    @Override
    public boolean hasLiked(Integer postId, Integer userId) {
        return jpa.existsByPostIdAndLikedBy(postId, userId);
    }

    @Override
    public long countLikes(Integer postId) {
        return jpa.countByPostId(postId);
    }

    @Override
    public Page<PostLikeEntity> findLikesByPost(Integer postId, Pageable pageable) {
        return jpa.findByPostId(postId, pageable)
                .map(PostLikeMapper::toDomain);
    }
}
