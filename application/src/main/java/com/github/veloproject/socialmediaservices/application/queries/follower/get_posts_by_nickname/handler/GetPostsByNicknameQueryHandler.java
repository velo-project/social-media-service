package com.github.veloproject.socialmediaservices.application.queries.follower.get_posts_by_nickname.handler;

import com.github.veloproject.socialmediaservices.application.abstractions.IPostRepository;
import com.github.veloproject.socialmediaservices.application.abstractions.IUserGRPCClient;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.handlers.NoAuthRequestHandler;
import com.github.veloproject.socialmediaservices.application.queries.follower.get_posts_by_nickname.GetPostsByNicknameQuery;
import com.github.veloproject.socialmediaservices.application.queries.follower.get_posts_by_nickname.GetPostsByNicknameQueryResult;
import com.github.veloproject.socialmediaservices.domain.exceptions.InvalidUserProvidedException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetPostsByNicknameQueryHandler extends NoAuthRequestHandler<GetPostsByNicknameQuery, GetPostsByNicknameQueryResult> {
    private final IPostRepository postRepository;
    private final IUserGRPCClient userGRPCClient;

    public GetPostsByNicknameQueryHandler(IPostRepository postRepository, IUserGRPCClient userGRPCClient) {
        this.postRepository = postRepository;
        this.userGRPCClient = userGRPCClient;
    }

    @Override
    public GetPostsByNicknameQueryResult handle(GetPostsByNicknameQuery request) {
        var user = userGRPCClient.getUserByNickname(request.nickname());
        Optional.ofNullable(user)
                .orElseThrow(InvalidUserProvidedException::new);
        var pageRequest = PageRequest.of(
                request.page(),
                20,
                Sort.by("postedIn").ascending());

        var posts = postRepository.findByUserId(user.id(), pageRequest).toList();

        return new GetPostsByNicknameQueryResult(200, "Posts listados.", posts);
    }
}
