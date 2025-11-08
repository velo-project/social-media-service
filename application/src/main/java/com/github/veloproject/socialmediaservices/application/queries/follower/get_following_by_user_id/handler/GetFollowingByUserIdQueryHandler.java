package com.github.veloproject.socialmediaservices.application.queries.follower.get_following_by_user_id.handler;

import com.github.veloproject.socialmediaservices.application.abstractions.IUserFollowerRepository;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.handlers.NoAuthRequestHandler;
import com.github.veloproject.socialmediaservices.application.queries.follower.get_following_by_user_id.GetFollowingByUserIdQuery;
import com.github.veloproject.socialmediaservices.application.queries.follower.get_following_by_user_id.GetFollowingByUserIdQueryResult;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class GetFollowingByUserIdQueryHandler extends NoAuthRequestHandler<GetFollowingByUserIdQuery, GetFollowingByUserIdQueryResult> {
    private final IUserFollowerRepository followerRepository;

    public GetFollowingByUserIdQueryHandler(IUserFollowerRepository followerRepository) {
        this.followerRepository = followerRepository;
    }

    @Override
    public GetFollowingByUserIdQueryResult handle(GetFollowingByUserIdQuery request) {
        var pageRequest = PageRequest.of(
                request.page(),
                20,
                Sort.by("followedAt").descending());

        var following = followerRepository.findAllByFollowerId(request.userId(), pageRequest).toList();

        return new GetFollowingByUserIdQueryResult(200, following);
    }
}
