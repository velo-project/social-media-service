package com.github.veloproject.socialmediaservices.application.queries.follower.get_followers_by_nickname;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Request;

public record GetFollowersByNicknameQuery(
        String nickname,
        Integer page
) implements Request<GetFollowersByNicknameQueryResult> {
}
