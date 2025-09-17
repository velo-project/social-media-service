package com.github.veloproject.socialmediaservices.application.commands.post.delete_post.handler;

import com.github.veloproject.socialmediaservices.application.abstractions.IPostRepository;
import com.github.veloproject.socialmediaservices.application.commands.post.delete_post.DeletePostCommand;
import com.github.veloproject.socialmediaservices.application.commands.post.delete_post.DeletePostCommandResult;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.handlers.AuthRequestHandler;
import com.github.veloproject.socialmediaservices.domain.exceptions.InvalidPostProvidedException;
import com.github.veloproject.socialmediaservices.domain.exceptions.UserNotAuthorException;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class DeletePostCommandHandler extends AuthRequestHandler<DeletePostCommand, DeletePostCommandResult> {
    private final IPostRepository postRepository;

    public DeletePostCommandHandler(IPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public DeletePostCommandResult handle(DeletePostCommand request,
                                          JwtAuthenticationToken token) {
        var post = postRepository
                .findById(request.postId());
        if (post.isEmpty())
            throw new InvalidPostProvidedException();

        var userId = Integer.valueOf(token.getToken().getSubject());
        if (post.get().getPostedBy().equals(userId))
            throw new UserNotAuthorException();

        postRepository.deleteById(request.postId());

        return new DeletePostCommandResult(200, "Post deletado com sucesso.");
    }
}
