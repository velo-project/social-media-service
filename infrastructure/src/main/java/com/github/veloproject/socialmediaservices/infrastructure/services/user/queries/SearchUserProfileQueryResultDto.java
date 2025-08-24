package com.github.veloproject.socialmediaservices.infrastructure.services.user.queries;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;
import com.github.veloproject.socialmediaservices.domain.entities.UserEntity;

public class SearchUserProfileQueryResultDto extends Response {
    private UserEntity user;

    public SearchUserProfileQueryResultDto(Integer statusCode, String message) {
        super(statusCode, message);
    }
}
