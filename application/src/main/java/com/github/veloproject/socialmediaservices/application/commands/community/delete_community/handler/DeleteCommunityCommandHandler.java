package com.github.veloproject.socialmediaservices.application.commands.community.delete_community.handler;

import com.github.veloproject.socialmediaservices.application.abstractions.ICommunityRepository;
import com.github.veloproject.socialmediaservices.application.commands.community.delete_community.DeleteCommunityCommand;
import com.github.veloproject.socialmediaservices.application.commands.community.delete_community.DeleteCommunityCommandResult;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.handlers.AuthRequestHandler;
import com.github.veloproject.socialmediaservices.domain.exceptions.InvalidCommunityProvidedException;
import com.github.veloproject.socialmediaservices.domain.exceptions.UserNotAuthorException;
import jakarta.transaction.Transactional;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class DeleteCommunityCommandHandler extends AuthRequestHandler<DeleteCommunityCommand, DeleteCommunityCommandResult> {
    private final ICommunityRepository communityRepository;

    public DeleteCommunityCommandHandler(ICommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }

    @Override
    @Transactional
    public DeleteCommunityCommandResult handle(DeleteCommunityCommand request, JwtAuthenticationToken token) {
        var community = communityRepository
                .findById(request.id())
                .orElseThrow(InvalidCommunityProvidedException::new);

        var userId = Integer.valueOf(token.getToken().getSubject());

        if (!community.getCreatedBy().equals(userId)) throw new UserNotAuthorException();
        else if (community.getIsDeleted()) throw new InvalidCommunityProvidedException();

        communityRepository.deleteById(request.id());

        return new DeleteCommunityCommandResult(200);
    }
}
