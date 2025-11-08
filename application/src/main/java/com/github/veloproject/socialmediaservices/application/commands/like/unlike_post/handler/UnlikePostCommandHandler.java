package com.github.veloproject.socialmediaservices.application.commands.like.unlike_post.handler;

import com.github.veloproject.socialmediaservices.application.abstractions.IPostLikeRepository;
import com.github.veloproject.socialmediaservices.application.commands.like.unlike_post.UnlikePostCommand;
import com.github.veloproject.socialmediaservices.application.commands.like.unlike_post.UnlikePostCommandResult;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.handlers.AuthRequestHandler;
import jakarta.transaction.Transactional;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UnlikePostCommandHandler extends AuthRequestHandler<UnlikePostCommand, UnlikePostCommandResult> {
    private final IPostLikeRepository postLikeRepository;

    public UnlikePostCommandHandler(IPostLikeRepository postLikeRepository) {
        this.postLikeRepository = postLikeRepository;
    }

    @Transactional
    @Override
    public UnlikePostCommandResult handle(UnlikePostCommand request, JwtAuthenticationToken token) {
        var userId = Integer.valueOf(token.getToken().getSubject());
        postLikeRepository
                .delete(request.postId(), userId);

        return new UnlikePostCommandResult(200);
    }
}
