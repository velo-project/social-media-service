package com.github.veloproject.socialmediaservices.infrastructure.services.user;

import com.github.veloproject.socialmediaservices.infrastructure.services.user.queries.SearchUserProfileQueryResultDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class UserServicesApiService {
    private final RestClient restClient;

    public UserServicesApiService(
            RestClient.Builder builder,
            @Value("${api.users.url}") String apiUsersUrl) {
        this.restClient = builder.baseUrl(apiUsersUrl).build();
    }

    public SearchUserProfileQueryResultDto getUserById(Integer id) {
        return restClient.get()
                .uri("/v2/search?id={id}", id)
                .retrieve()
                .body(SearchUserProfileQueryResultDto.class);
    }
}
