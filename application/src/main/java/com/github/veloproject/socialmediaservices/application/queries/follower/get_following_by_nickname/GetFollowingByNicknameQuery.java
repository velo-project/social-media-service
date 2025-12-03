package com.github.veloproject.socialmediaservices.application.queries.follower.get_following_by_nickname;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Request;

public record GetFollowingByNicknameQuery(
        String nickname,
        Integer page
) implements Request<GetFollowingByNicknameQueryResult> {
}
