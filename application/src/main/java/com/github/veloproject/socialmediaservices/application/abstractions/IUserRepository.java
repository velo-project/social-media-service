package com.github.veloproject.socialmediaservices.application.abstractions;

import com.github.veloproject.socialmediaservices.domain.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface IUserRepository {
    Optional<UserEntity> findById(Integer id);
}
