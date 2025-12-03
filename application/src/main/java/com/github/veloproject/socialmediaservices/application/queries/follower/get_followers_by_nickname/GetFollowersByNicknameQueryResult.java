package com.github.veloproject.socialmediaservices.application.queries.follower.get_followers_by_nickname;

import com.github.veloproject.socialmediaservices.application.dto.UserInfo;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;
import com.github.veloproject.socialmediaservices.domain.entities.UserFollowerEntity;
import lombok.Getter;

import java.util.List;

@Getter
public class GetFollowersByNicknameQueryResult extends Response {
    private final List<UserInfo> followers;

    public GetFollowersByNicknameQueryResult(Integer statusCode,
                                             String message,
                                             List<UserInfo> followers) {
        super(statusCode, message);
        this.followers = followers;
    }
}
