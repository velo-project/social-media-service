package com.github.veloproject.socialmediaservices.application.abstractions;

import com.github.veloproject.socialmediaservices.domain.entities.PostEntity;

public interface IPostRepository {
    Integer save(PostEntity user);
}
