package com.github.veloproject.socialmediaservices.application.queries.follower.get_posts_by_nickname;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Request;

public record GetPostsByNicknameQuery(
        String nickname,
        Integer page
) implements Request<GetPostsByNicknameQueryResult> {
}
