package com.github.veloproject.socialmediaservices.application.commands.post.delete_post.handler;

import com.github.veloproject.socialmediaservices.application.abstractions.IPostRepository;
import com.github.veloproject.socialmediaservices.application.commands.post.delete_post.DeletePostByUserCommand;
import com.github.veloproject.socialmediaservices.application.commands.post.delete_post.DeletePostByUserCommandResult;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.handlers.AuthRequestHandler;
import com.github.veloproject.socialmediaservices.domain.exceptions.InvalidPostProvidedException;
import com.github.veloproject.socialmediaservices.domain.exceptions.UserNotAuthorException;
import jakarta.transaction.Transactional;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class DeletePostByUserCommandHandler extends AuthRequestHandler<DeletePostByUserCommand, DeletePostByUserCommandResult> {
    private final IPostRepository postRepository;

    public DeletePostByUserCommandHandler(IPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    @Override
    public DeletePostByUserCommandResult handle(DeletePostByUserCommand request,
                                                JwtAuthenticationToken token) {
        var post = postRepository
                .findById(request.postId()).orElseThrow(InvalidPostProvidedException::new);

        var userId = Integer.valueOf(token.getToken().getSubject());
        if (!post.getPostedBy().equals(userId))
            throw new UserNotAuthorException();

        postRepository.deleteById(request.postId());

        return new DeletePostByUserCommandResult(200);
    }
}
