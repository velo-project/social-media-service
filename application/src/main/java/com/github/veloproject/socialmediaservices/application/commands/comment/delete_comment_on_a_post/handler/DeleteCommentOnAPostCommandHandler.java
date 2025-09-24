package com.github.veloproject.socialmediaservices.application.commands.comment.delete_comment_on_a_post.handler;

import com.github.veloproject.socialmediaservices.application.abstractions.ICommentRepository;
import com.github.veloproject.socialmediaservices.application.commands.comment.delete_comment_on_a_post.DeleteCommentOnAPostCommand;
import com.github.veloproject.socialmediaservices.application.commands.comment.delete_comment_on_a_post.DeleteCommentOnAPostCommandResult;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.handlers.AuthRequestHandler;
import com.github.veloproject.socialmediaservices.domain.exceptions.InvalidPostProvidedException;
import com.github.veloproject.socialmediaservices.domain.exceptions.UserNotAuthorException;
import jakarta.transaction.Transactional;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class DeleteCommentOnAPostCommandHandler extends AuthRequestHandler<DeleteCommentOnAPostCommand, DeleteCommentOnAPostCommandResult> {
    private final ICommentRepository commentRepository;

    public DeleteCommentOnAPostCommandHandler(ICommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Transactional
    @Override
    public DeleteCommentOnAPostCommandResult handle(DeleteCommentOnAPostCommand request, JwtAuthenticationToken token) {
        var comment = commentRepository
                .findById(request.commentId()).orElseThrow(InvalidPostProvidedException::new);

        var userId = Integer.valueOf(token.getToken().getSubject());
        if (!comment.getCommentedBy().equals(userId))
            throw new UserNotAuthorException();

        commentRepository.deleteById(request.commentId());

        return new DeleteCommentOnAPostCommandResult(200);
    }
}
