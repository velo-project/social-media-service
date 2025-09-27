package com.github.veloproject.socialmediaservices.application.commands.community_member.join_community;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;

public class JoinCommunityCommandResult extends Response {
    public JoinCommunityCommandResult(Integer statusCode) {
        super(statusCode);
    }
}
