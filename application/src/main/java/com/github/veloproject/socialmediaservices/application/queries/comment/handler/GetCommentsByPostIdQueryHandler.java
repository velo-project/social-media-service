package com.github.veloproject.socialmediaservices.application.queries.comment.handler;

import com.github.veloproject.socialmediaservices.application.abstractions.ICommentRepository;
import com.github.veloproject.socialmediaservices.application.abstractions.IPostRepository;
import com.github.veloproject.socialmediaservices.application.mediators.contracts.handlers.NoAuthRequestHandler;
import com.github.veloproject.socialmediaservices.application.queries.comment.GetCommentsByPostIdQuery;
import com.github.veloproject.socialmediaservices.application.queries.comment.GetCommentsByPostIdQueryResult;
import com.github.veloproject.socialmediaservices.domain.exceptions.InvalidPostProvidedException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class GetCommentsByPostIdQueryHandler extends NoAuthRequestHandler<GetCommentsByPostIdQuery, GetCommentsByPostIdQueryResult> {
    private final IPostRepository postRepository;
    private final ICommentRepository commentRepository;

    public GetCommentsByPostIdQueryHandler(ICommentRepository commentRepository,
                                           IPostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public GetCommentsByPostIdQueryResult handle(GetCommentsByPostIdQuery request) {
        if (!postRepository.existsById(request.postId())) throw new InvalidPostProvidedException();

        var pageRequest = PageRequest.of(
                request.page(),
                20);
        var comments = commentRepository.findByPostId(request.postId(), pageRequest)
                .stream().toList();

        return new GetCommentsByPostIdQueryResult(200, comments);
    }
}
