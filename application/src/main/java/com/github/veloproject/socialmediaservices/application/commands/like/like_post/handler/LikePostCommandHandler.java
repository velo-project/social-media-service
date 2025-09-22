package com.github.veloproject.socialmediaservices.application.commands.like.like_post.handler;

import com.github.veloproject.socialmediaservices.application.abstractions.IPostLikeRepository;
import com.github.veloproject.socialmediaservices.application.commands.like.like_post.LikePostCommand;
import com.github.veloproject.socialmediaservices.application.commands.like.like_post.LikePostCommandResult;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.handlers.AuthRequestHandler;
import com.github.veloproject.socialmediaservices.domain.entities.PostLikeEntity;
import jakarta.transaction.Transactional;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LikePostCommandHandler extends AuthRequestHandler<LikePostCommand, LikePostCommandResult> {
    private final IPostLikeRepository postLikeRepository;

    public LikePostCommandHandler(IPostLikeRepository postLikeRepository) {
        this.postLikeRepository = postLikeRepository;
    }

    @Transactional
    @Override
    public LikePostCommandResult handle(LikePostCommand request, JwtAuthenticationToken token) {
        var userId = Integer.valueOf(token.getToken().getSubject());
        var postLike = PostLikeEntity.builder()
                .postId(request.postId())
                .likedBy(userId)
                .build();

        postLikeRepository.save(postLike);

        return new LikePostCommandResult(200);
    }
}
