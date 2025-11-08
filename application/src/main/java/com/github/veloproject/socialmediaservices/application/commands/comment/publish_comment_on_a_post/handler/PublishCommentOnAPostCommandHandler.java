package com.github.veloproject.socialmediaservices.application.commands.comment.publish_comment_on_a_post.handler;

import com.github.veloproject.socialmediaservices.application.abstractions.ICommentRepository;
import com.github.veloproject.socialmediaservices.application.abstractions.IPostRepository;
import com.github.veloproject.socialmediaservices.application.abstractions.IUserGRPCClient;
import com.github.veloproject.socialmediaservices.application.commands.comment.publish_comment_on_a_post.PublishCommentOnAPostCommand;
import com.github.veloproject.socialmediaservices.application.commands.comment.publish_comment_on_a_post.PublishCommentOnAPostCommandResult;
import com.github.veloproject.socialmediaservices.application.dto.UserInfo;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.handlers.AuthRequestHandler;
import com.github.veloproject.socialmediaservices.domain.entities.CommentEntity;
import com.github.veloproject.socialmediaservices.domain.exceptions.InvalidPostProvidedException;
import com.github.veloproject.socialmediaservices.domain.exceptions.InvalidUserProvidedException;
import jakarta.transaction.Transactional;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PublishCommentOnAPostCommandHandler
        extends AuthRequestHandler<PublishCommentOnAPostCommand, PublishCommentOnAPostCommandResult> {
    private final ICommentRepository commentRepository;
    private final IUserGRPCClient userServices;
    private final IPostRepository postRepository;

    public PublishCommentOnAPostCommandHandler(ICommentRepository commentRepository,
                                               IUserGRPCClient userServices,
                                               IPostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userServices = userServices;
        this.postRepository = postRepository;
    }

    @Transactional
    @Override
    public PublishCommentOnAPostCommandResult handle(PublishCommentOnAPostCommand request, JwtAuthenticationToken token) {
        if (!postRepository.existsById(request.postId())) throw new InvalidPostProvidedException();

        var user = getUserByToken(token);

        var comment = CommentEntity.builder()
                .postId(request.postId())
                .content(request.content())
                .commentedBy(user.id())
                .build();
        var id = commentRepository.save(comment);

        return new PublishCommentOnAPostCommandResult(200, id);
    }

    private UserInfo getUserByToken(JwtAuthenticationToken token) throws InvalidUserProvidedException {
        var integerSubject = Integer.valueOf(
                token.getToken()
                        .getSubject());

        var user = userServices
                .getUserById(integerSubject);

        return Optional.ofNullable(user)
                .orElseThrow(InvalidUserProvidedException::new);
    }
}
