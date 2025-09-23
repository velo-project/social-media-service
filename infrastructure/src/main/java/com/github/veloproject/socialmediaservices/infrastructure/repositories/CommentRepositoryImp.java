package com.github.veloproject.socialmediaservices.infrastructure.repositories;

import com.github.veloproject.socialmediaservices.application.abstractions.ICommentRepository;
import com.github.veloproject.socialmediaservices.domain.entities.CommentEntity;
import com.github.veloproject.socialmediaservices.infrastructure.mappers.CommentMapper;
import com.github.veloproject.socialmediaservices.infrastructure.repositories.jpa.ICommentRepositoryJpa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CommentRepositoryImp implements ICommentRepository {
    private final ICommentRepositoryJpa jpa;

    public CommentRepositoryImp(ICommentRepositoryJpa jpa) {
        this.jpa = jpa;
    }

    @Override
    public Integer save(CommentEntity entity) {
        var table = CommentMapper.toPersistence(entity);
        return jpa.save(table).getId();
    }

    @Override
    public void deleteById(Integer commentId) {
        jpa.deleteById(commentId);
    }

    @Override
    public Optional<CommentEntity> findById(Integer commentId) {
        return jpa.findById(commentId).map(CommentMapper::toDomain);
    }

    @Override
    public Page<CommentEntity> findByPostId(Integer postId, Pageable pageable) {
        return jpa.findByPostId(postId, pageable).map(CommentMapper::toDomain);
    }

    @Override
    public long countByPostId(Integer postId) {
        return jpa.countByPostId(postId);
    }
}
