package com.github.veloproject.socialmediaservices.presentation.controllers.post;

import com.github.veloproject.socialmediaservices.application.mediators.implementations.LoggingMediatorImp;
import com.github.veloproject.socialmediaservices.application.queries.get_post_by_id.GetPostByIdQuery;
import com.github.veloproject.socialmediaservices.application.queries.get_post_by_id.GetPostByIdQueryResult;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/social_media/post")
public class GetPostByIdController {
    private final LoggingMediatorImp mediator;
    public GetPostByIdController(LoggingMediatorImp mediator) {this.mediator = mediator;}
    @GetMapping("/v1/{id}")
    public ResponseEntity<GetPostByIdQueryResult> getPostById (
            @PathParam("id") Integer id
    ) {
        var query = new GetPostByIdQuery(id);
        var response = mediator.send(query);

        return ResponseEntity
                .status(response.getStatusCode())
                .body(response);
    }
}
