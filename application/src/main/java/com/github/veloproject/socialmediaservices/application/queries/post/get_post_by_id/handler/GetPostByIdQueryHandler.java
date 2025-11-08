package com.github.veloproject.socialmediaservices.application.queries.post.get_post_by_id.handler;

import com.github.veloproject.socialmediaservices.application.abstractions.IPostRepository;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.handlers.NoAuthRequestHandler;
import com.github.veloproject.socialmediaservices.application.queries.post.get_post_by_id.GetPostByIdQuery;
import com.github.veloproject.socialmediaservices.application.queries.post.get_post_by_id.GetPostByIdQueryResult;
import com.github.veloproject.socialmediaservices.domain.exceptions.InvalidPostProvidedException;
import org.springframework.stereotype.Service;

@Service
public class GetPostByIdQueryHandler extends NoAuthRequestHandler<GetPostByIdQuery, GetPostByIdQueryResult> {
    private final IPostRepository postRepository;

    public GetPostByIdQueryHandler (IPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public GetPostByIdQueryResult handle(GetPostByIdQuery request) {
        var post = postRepository
                .findById(request.id())
                .orElseThrow(InvalidPostProvidedException::new);

        return new GetPostByIdQueryResult(200, "Post encontrado.", post);
    }

}
