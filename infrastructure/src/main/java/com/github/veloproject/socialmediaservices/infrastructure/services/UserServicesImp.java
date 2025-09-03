package com.github.veloproject.socialmediaservices.infrastructure.services;

import com.github.veloproject.socialmediaservices.application.abstractions.IUserServices;
import com.github.veloproject.socialmediaservices.domain.entities.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class UserServicesImp implements IUserServices {
    private final RestClient client;

    public UserServicesImp() {
        this.client = RestClient.create();
    }

    public UserEntity getUserById(Integer id) {
        var uri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("u-nginx")
                .port(80)
                .path("/api/user/v2/search")
                .queryParam("id", id)
                .build()
                .toUri();

        var response = client.get()
                .uri(uri)
                .retrieve()
                .body(GetUserByIdResponse.class);

        if (response != null) return response.user();

        return null;
    }

    public record GetUserByIdResponse(UserEntity user)  {}
}


