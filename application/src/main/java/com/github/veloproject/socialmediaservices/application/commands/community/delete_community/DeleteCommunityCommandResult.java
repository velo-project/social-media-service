package com.github.veloproject.socialmediaservices.application.commands.community.delete_community;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;

public class DeleteCommunityCommandResult extends Response {


    public DeleteCommunityCommandResult(Integer statusCode) {
        super(statusCode);
    }
}
