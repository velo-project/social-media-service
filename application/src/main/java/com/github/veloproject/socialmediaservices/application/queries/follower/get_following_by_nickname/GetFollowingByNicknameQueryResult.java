package com.github.veloproject.socialmediaservices.application.queries.follower.get_following_by_nickname;

import com.github.veloproject.socialmediaservices.application.dto.UserInfo;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;
import com.github.veloproject.socialmediaservices.domain.entities.UserFollowerEntity;
import lombok.Getter;

import java.util.List;

@Getter
public class GetFollowingByNicknameQueryResult extends Response {
    private final List<UserInfo> following;

    public GetFollowingByNicknameQueryResult(Integer statusCode, String message, List<UserInfo> following) {
        super(statusCode, message);
        this.following = following;
    }
}
