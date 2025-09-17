package com.github.veloproject.socialmediaservices.application.queries.get_posts_by_user_id;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Request;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetPostsByUserIdQuery implements Request<GetPostsByUserIdQueryResult> {
    private Integer userId;
}
