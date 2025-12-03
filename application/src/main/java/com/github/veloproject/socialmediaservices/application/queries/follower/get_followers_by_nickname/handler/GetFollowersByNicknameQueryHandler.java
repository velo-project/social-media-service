package com.github.veloproject.socialmediaservices.application.queries.follower.get_followers_by_nickname.handler;

import com.github.veloproject.socialmediaservices.application.abstractions.IUserFollowerRepository;
import com.github.veloproject.socialmediaservices.application.abstractions.IUserGRPCClient;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.handlers.NoAuthRequestHandler;
import com.github.veloproject.socialmediaservices.application.queries.follower.get_followers_by_nickname.GetFollowersByNicknameQuery;
import com.github.veloproject.socialmediaservices.application.queries.follower.get_followers_by_nickname.GetFollowersByNicknameQueryResult;
import com.github.veloproject.socialmediaservices.domain.entities.UserFollowerEntity;
import com.github.veloproject.socialmediaservices.domain.exceptions.InvalidUserProvidedException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetFollowersByNicknameQueryHandler extends NoAuthRequestHandler<GetFollowersByNicknameQuery, GetFollowersByNicknameQueryResult> {
    private final IUserFollowerRepository followerRepository;
    private final IUserGRPCClient userGRPCClient;

    public GetFollowersByNicknameQueryHandler(IUserFollowerRepository followerRepository,
                                              IUserGRPCClient userGRPCClient) {
        this.followerRepository = followerRepository;
        this.userGRPCClient = userGRPCClient;
    }

    @Override
    public GetFollowersByNicknameQueryResult handle(GetFollowersByNicknameQuery request) {
        var targetUserId = Optional.ofNullable(userGRPCClient.getUserByNickname(request.nickname())).orElseThrow(InvalidUserProvidedException::new);

        var pageRequest = PageRequest.of(
                request.page(),
                20,
                Sort.by("followedAt").descending());

        var followersRelationship = followerRepository.findAllByUserId(targetUserId.id(), pageRequest);

        var followerIds = followersRelationship.stream()
                .map(UserFollowerEntity::getFollowerId)
                .toList();

        var followersDetails = userGRPCClient.getUsersByIdList(followerIds);

        return new GetFollowersByNicknameQueryResult(200, "Seguidores listados.", followersDetails);
    }
}
