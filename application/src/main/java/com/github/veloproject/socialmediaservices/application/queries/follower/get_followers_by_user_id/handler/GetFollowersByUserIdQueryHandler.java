package com.github.veloproject.socialmediaservices.application.queries.follower.get_followers_by_user_id.handler;

import com.github.veloproject.socialmediaservices.application.abstractions.IUserFollowerRepository;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.handlers.NoAuthRequestHandler;
import com.github.veloproject.socialmediaservices.application.queries.follower.get_followers_by_user_id.GetFollowersByUserIdQuery;
import com.github.veloproject.socialmediaservices.application.queries.follower.get_followers_by_user_id.GetFollowersByUserIdQueryResult;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class GetFollowersByUserIdQueryHandler extends NoAuthRequestHandler<GetFollowersByUserIdQuery, GetFollowersByUserIdQueryResult> {
    private final IUserFollowerRepository followerRepository;

    public GetFollowersByUserIdQueryHandler(IUserFollowerRepository followerRepository) {
        this.followerRepository = followerRepository;
    }

    @Override
    public GetFollowersByUserIdQueryResult handle(GetFollowersByUserIdQuery request) {
        var pageRequest = PageRequest.of(
                request.page(),
                20,
                Sort.by("followedAt").descending());

        var followers = followerRepository.findAllByUserId(request.userId(), pageRequest).toList();

        return new GetFollowersByUserIdQueryResult(200, followers);
    }
}
