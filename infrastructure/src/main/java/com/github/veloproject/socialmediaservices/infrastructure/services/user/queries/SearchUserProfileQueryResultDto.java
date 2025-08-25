package com.github.veloproject.socialmediaservices.infrastructure.services.user.queries;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;
import com.github.veloproject.socialmediaservices.domain.entities.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchUserProfileQueryResultDto extends Response {
    private UserEntity user;

    public SearchUserProfileQueryResultDto(Integer statusCode, String message, UserEntity user) {
        super(statusCode, message);
        this.user = user;
    }
}
