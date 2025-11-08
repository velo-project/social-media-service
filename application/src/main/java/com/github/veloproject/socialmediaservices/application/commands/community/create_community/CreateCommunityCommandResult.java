package com.github.veloproject.socialmediaservices.application.commands.community.create_community;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;
import lombok.Getter;

@Getter
public class CreateCommunityCommandResult extends Response {
    private final Integer communityId;

    public CreateCommunityCommandResult(Integer statusCode, Integer communityId) {
        super(statusCode);
        this.communityId = communityId;
    }
}
