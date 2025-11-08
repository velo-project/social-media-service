package com.github.veloproject.socialmediaservices.application.queries.comment;

import com.github.veloproject.socialmediaservices.application.mediators.contracts.Response;
import com.github.veloproject.socialmediaservices.domain.entities.CommentEntity;
import lombok.Getter;

import java.util.List;

@Getter
public class GetCommentsByPostIdQueryResult extends Response {
    private final List<CommentEntity> comments;

    public GetCommentsByPostIdQueryResult(Integer statusCode,
                                          List<CommentEntity> comments) {
        super(statusCode);
        this.comments = comments;
    }
}
