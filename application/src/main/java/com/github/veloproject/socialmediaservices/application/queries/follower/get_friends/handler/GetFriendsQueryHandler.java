package com.github.veloproject.socialmediaservices.application.queries.follower.get_friends.handler;

import com.github.veloproject.socialmediaservices.application.abstractions.IUserFollowerRepository;
import com.github.veloproject.socialmediaservices.application.abstractions.IUserGRPCClient;
import com.github.veloproject.socialmediaservices.application.dto.UserInfo;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.handlers.AuthRequestHandler;
import com.github.veloproject.socialmediaservices.application.queries.follower.get_friends.GetFriendsQuery;
import com.github.veloproject.socialmediaservices.application.queries.follower.get_friends.GetFriendsQueryResult;
import com.github.veloproject.socialmediaservices.domain.exceptions.InvalidUserProvidedException;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetFriendsQueryHandler extends AuthRequestHandler<GetFriendsQuery, GetFriendsQueryResult> {
    private final IUserGRPCClient userGRPCClient;
    private final IUserFollowerRepository userFollowerRepository;

    public GetFriendsQueryHandler(IUserFollowerRepository userFollowerRepository,
                                  IUserGRPCClient userGRPCClient) {
        this.userFollowerRepository = userFollowerRepository;
        this.userGRPCClient = userGRPCClient;
    }

    @Override
    public GetFriendsQueryResult handle(GetFriendsQuery request, JwtAuthenticationToken token) {
        var userId = Optional.of(Integer.valueOf(token.getToken().getSubject()))
                .orElseThrow(InvalidUserProvidedException::new);

        if (!userGRPCClient.existsByUserId(userId)) throw new InvalidUserProvidedException();

        List<Integer> friendIds = userFollowerRepository.getFriends(userId);

        if (friendIds.isEmpty()) {
            return new GetFriendsQueryResult(200, "Nenhum amigo encontrado.", List.of());
        }

        List<UserInfo> friendsDetails = userGRPCClient.getUsersByIdList(friendIds);

        var responseList = friendsDetails.stream()
                .map(UserInfo::nickname)
                .toList();

        return new GetFriendsQueryResult(200, "Amigos listados.", responseList);
    }
}
