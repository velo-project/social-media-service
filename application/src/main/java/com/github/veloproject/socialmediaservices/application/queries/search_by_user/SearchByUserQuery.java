package com.github.veloproject.socialmediaservices.application.queries.search_by_user;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Request;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SearchByUserQuery implements Request<SearchByUserQueryResult> {
    private Integer userId;
}
