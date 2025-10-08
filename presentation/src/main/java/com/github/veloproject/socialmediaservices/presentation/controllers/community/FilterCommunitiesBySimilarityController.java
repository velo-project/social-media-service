package com.github.veloproject.socialmediaservices.presentation.controllers.community;

import com.github.veloproject.socialmediaservices.application.mediators.implementations.LoggingMediatorImp;
import com.github.veloproject.socialmediaservices.application.queries.community.get_communities_by_similarity.FilterCommunitiesBySimilarityQuery;
import com.github.veloproject.socialmediaservices.application.queries.community.get_communities_by_similarity.FilterCommunitiesBySimilarityQueryResult;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/social_media/communities")
public class FilterCommunitiesBySimilarityController {
    private final LoggingMediatorImp mediator;

    public FilterCommunitiesBySimilarityController(LoggingMediatorImp mediator) {
        this.mediator = mediator;
    }

    @GetMapping("/v1/search")
    public ResponseEntity<FilterCommunitiesBySimilarityQueryResult> searchCommunitiesBySimilarity(
            @RequestParam @NotNull String queryContent
    ) {
        var query = new FilterCommunitiesBySimilarityQuery(queryContent);
        var response = mediator.send(query);

        return ResponseEntity
                .status(response.getStatusCode())
                .body(response);
    }
}
