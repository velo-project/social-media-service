package com.github.veloproject.socialmediaservices.application.queries.get_posts_by_user_id;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;
import com.github.veloproject.socialmediaservices.domain.entities.PostEntity;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class GetPostsByUserIdQueryResult extends Response {
    private final Page<PostEntity> posts;

    public GetPostsByUserIdQueryResult(Integer statusCode, Page<PostEntity> posts) {
        super(statusCode);
        this.posts = posts;
    }
}
