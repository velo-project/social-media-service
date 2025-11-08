package com.github.veloproject.socialmediaservices.application.mediators.contracts;

public interface Mediator {
    <TResponse extends Response> TResponse send(Request<TResponse> request);
}