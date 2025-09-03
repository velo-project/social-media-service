package com.github.veloproject.socialmediaservices.application.abstractions;

import com.github.veloproject.socialmediaservices.domain.entities.UserEntity;

public interface IUserServices {
    UserEntity getUserById(Integer id);
}
