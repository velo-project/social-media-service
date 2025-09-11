package com.github.veloproject.socialmediaservices.application.queries.search_by_user;

import com.github.veloproject.socialmediaservices.application.dto.UserInfo;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;
import lombok.Getter;

@Getter
public class SearchByUserQueryResult extends Response {
    private UserInfo userInfo;

    public SearchByUserQueryResult(UserInfo userInfo, Integer statusCode, String message) {
        super(statusCode, message);
        this.userInfo = userInfo;
    }
}
