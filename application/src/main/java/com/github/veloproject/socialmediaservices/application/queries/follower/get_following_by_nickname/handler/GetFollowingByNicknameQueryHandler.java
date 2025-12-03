package com.github.veloproject.socialmediaservices.application.queries.follower.get_following_by_nickname.handler;

import com.github.veloproject.socialmediaservices.application.abstractions.IUserFollowerRepository;
import com.github.veloproject.socialmediaservices.application.abstractions.IUserGRPCClient;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.handlers.NoAuthRequestHandler;
import com.github.veloproject.socialmediaservices.application.queries.follower.get_following_by_nickname.GetFollowingByNicknameQuery;
import com.github.veloproject.socialmediaservices.application.queries.follower.get_following_by_nickname.GetFollowingByNicknameQueryResult;
import com.github.veloproject.socialmediaservices.domain.entities.UserFollowerEntity;
import com.github.veloproject.socialmediaservices.domain.exceptions.InvalidUserProvidedException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetFollowingByNicknameQueryHandler extends NoAuthRequestHandler<GetFollowingByNicknameQuery, GetFollowingByNicknameQueryResult> {
    private final IUserFollowerRepository followerRepository;
    private final IUserGRPCClient userGRPCClient;

    public GetFollowingByNicknameQueryHandler(IUserFollowerRepository followerRepository,
                                              IUserGRPCClient userGRPCClient) {
        this.followerRepository = followerRepository;
        this.userGRPCClient = userGRPCClient;
    }

    @Override
    public GetFollowingByNicknameQueryResult handle(GetFollowingByNicknameQuery request) {
        var targetUser = Optional.ofNullable(userGRPCClient.getUserByNickname(request.nickname()))
                .orElseThrow(InvalidUserProvidedException::new);

        var pageRequest = PageRequest.of(
                request.page(),
                20,
                Sort.by("followedAt").descending());

        var followingRelationship = followerRepository.findAllByFollowerId(targetUser.id(), pageRequest);

        var followingIds = followingRelationship.stream()
                .map(UserFollowerEntity::getUserId)
                .toList();

        // 5. Buscar detalhes desses usuários seguidos
        var followingDetails = userGRPCClient.getUsersByIdList(followingIds);

        return new GetFollowingByNicknameQueryResult(200, "Seguindo listados.", followingDetails);
    }
}
