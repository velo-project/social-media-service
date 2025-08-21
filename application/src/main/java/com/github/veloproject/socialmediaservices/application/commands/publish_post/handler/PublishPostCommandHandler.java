package com.github.veloproject.socialmediaservices.application.commands.publish_post.handler;

import com.github.veloproject.socialmediaservices.application.commands.publish_post.PublishPostCommand;
import com.github.veloproject.socialmediaservices.application.commands.publish_post.PublishPostCommandResult;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.handlers.AuthRequestHandler;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class PublishPostCommandHandler extends AuthRequestHandler<PublishPostCommand, PublishPostCommandResult> {
    @Override
    public PublishPostCommandResult handle(PublishPostCommand request, JwtAuthenticationToken token) {
        return null;
    }
}
