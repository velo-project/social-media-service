package com.github.veloproject.socialmediaservices.infrastructure.repositories;

import com.github.veloproject.socialmediaservices.application.abstractions.IUserFollowerRepository;
import com.github.veloproject.socialmediaservices.domain.entities.UserFollowerEntity;
import com.github.veloproject.socialmediaservices.infrastructure.mappers.UserFollowerMapper;
import com.github.veloproject.socialmediaservices.infrastructure.repositories.jpa.IUserFollowerRepositoryJpa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserFollowerRepositoryImpl implements IUserFollowerRepository {
    private final IUserFollowerRepositoryJpa jpa;

    public UserFollowerRepositoryImpl(IUserFollowerRepositoryJpa jpa) {
        this.jpa = jpa;
    }

    @Override
    public void save(UserFollowerEntity entity) {
        var eMapped = UserFollowerMapper
                .toPersistence(entity);

        jpa.save(eMapped);
    }

    @Override
    public Page<UserFollowerEntity> findAllByUserId(Integer userId, Pageable pageable) {
        return jpa.findAllByUserId(userId, pageable)
                .map(UserFollowerMapper::toDomain);
    }

    @Override
    public Page<UserFollowerEntity> findAllByFollowerId(Integer userId, Pageable pageable) {
        return jpa.findAllByFollowerId(userId, pageable)
                .map(UserFollowerMapper::toDomain);
    }

    @Override
    public void delete(Integer userId, Integer followerId) {
        jpa.deleteByUserIdAndFollowerId(userId, followerId);
    }

    @Override
    public List<Integer> getFriends(Integer userId) {
        return jpa.getFriends(userId);
    }
}
