package com.github.veloproject.socialmediaservices.infrastructure.repositories;

import com.github.veloproject.socialmediaservices.application.abstractions.IUserRepository;
import com.github.veloproject.socialmediaservices.domain.entities.UserEntity;
import com.github.veloproject.socialmediaservices.infrastructure.mappers.UserMapper;
import com.github.veloproject.socialmediaservices.infrastructure.repositories.jpa.IUserRepositoryJpa;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImp implements IUserRepository {
    private final IUserRepositoryJpa jpa;

    public UserRepositoryImp(IUserRepositoryJpa jpa) {
        this.jpa = jpa;
    }

    @Override
    public Optional<UserEntity> findById(Integer id) {
        var user = jpa.findById(id);

        return user.map(UserMapper::toDomain);
    }
}
