package com.github.veloproject.socialmediaservices.presentation.controllers.post;

import com.github.veloproject.socialmediaservices.application.mediators.implementations.LoggingMediatorImp;
import com.github.veloproject.socialmediaservices.application.queries.post.get_post_by_id.GetPostByIdQuery;
import com.github.veloproject.socialmediaservices.application.queries.post.get_post_by_id.GetPostByIdQueryResult;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/social_media/posts")
public class GetPostByIdController {
    private final LoggingMediatorImp mediator;

    public GetPostByIdController(LoggingMediatorImp mediator) {this.mediator = mediator;}

    @GetMapping("/v1/post")
    public ResponseEntity<GetPostByIdQueryResult> getPostById (
            @RequestParam("id") @Valid @Positive Integer id) {
        var query = new GetPostByIdQuery(id);
        var response = mediator.send(query);

        return ResponseEntity
                .status(response.getStatusCode())
                .body(response);
    }
}
