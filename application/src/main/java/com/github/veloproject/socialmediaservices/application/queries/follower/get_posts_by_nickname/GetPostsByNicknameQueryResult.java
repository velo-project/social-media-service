package com.github.veloproject.socialmediaservices.application.queries.follower.get_posts_by_nickname;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;
import com.github.veloproject.socialmediaservices.domain.entities.PostEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetPostsByNicknameQueryResult extends Response {
    private final List<PostEntity> posts;

    public GetPostsByNicknameQueryResult(Integer statusCode, String message, List<PostEntity> posts) {
        super(statusCode, message);
        this.posts = posts;
    }
}
