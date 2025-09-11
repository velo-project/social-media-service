package com.github.veloproject.socialmediaservices.application.queries.search_by_user.handler;

import com.github.veloproject.socialmediaservices.application.abstractions.IUserServices;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.handlers.AuthRequestHandler;
import com.github.veloproject.socialmediaservices.application.queries.search_by_user.SearchByUserQuery;
import com.github.veloproject.socialmediaservices.application.queries.search_by_user.SearchByUserQueryResult;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class SearchByUserQueryHandler extends AuthRequestHandler<SearchByUserQuery, SearchByUserQueryResult> {
    private final IUserServices services;

    public SearchByUserQueryHandler(IUserServices services) {
        this.services = services;
    }

    @Override
    public SearchByUserQueryResult handle(SearchByUserQuery request, JwtAuthenticationToken token) {
        var InfoUser = services.getUserById(request.getUserId());
        if (InfoUser == null) {
            throw new UserNotFoundException("User not found");
        }
        return new SearchByUserQueryResult(InfoUser,200, "user found.");
    }

}
