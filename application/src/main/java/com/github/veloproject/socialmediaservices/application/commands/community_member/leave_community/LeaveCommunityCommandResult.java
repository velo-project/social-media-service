package com.github.veloproject.socialmediaservices.application.commands.community_member.leave_community;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;

public class LeaveCommunityCommandResult extends Response {
    public LeaveCommunityCommandResult(Integer statusCode) {
        super(statusCode);
    }
}
