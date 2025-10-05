package com.github.veloproject.socialmediaservices.infrastructure.services.user;

import com.github.veloproject.socialmediaservices.application.abstractions.IUserServices;
import com.github.veloproject.socialmediaservices.application.dto.UserInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class UserServicesImp implements IUserServices {
    private final RestClient client;

    public UserServicesImp() {
        this.client = RestClient.create();
    }

    public UserInfo getUserById(Integer id) {
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

        if (response != null) return new UserInfo(
                response.user.id(),
                response.user.name(),
                response.user.nickname(),
                response.user.bannerPhotoUrl(),
                response.user.profilePhotoUrl(),
                response.user.isBlocked(),
                response.user.isDeleted()
        );

        return null;
    }

    // TODO
    @Override
    public boolean existsById(Integer id) {
        return true;
    }

    // TODO getUsersById

    public record GetUserByIdResponse(UserDTO user)  {}
}


