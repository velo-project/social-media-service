package com.github.veloproject.socialmediaservices.application.abstractions;

import com.github.veloproject.socialmediaservices.domain.entities.UserEntity;

import java.util.Optional;

public interface IUserRepository {
    Optional<UserEntity> findById(Integer id);
}
