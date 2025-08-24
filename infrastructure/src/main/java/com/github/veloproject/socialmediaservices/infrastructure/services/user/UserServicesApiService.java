package com.github.veloproject.socialmediaservices.infrastructure.services.user;

import com.github.veloproject.socialmediaservices.infrastructure.services.user.queries.SearchUserProfileQueryResultDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserServicesApiService {
    @Value("${api.users.url}")
    private String apiUsersUrl;

    private final WebClient webClient;

    public UserServicesApiService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl(apiUsersUrl).build();
    }

    // TODO getUserById Route
    public Mono<SearchUserProfileQueryResultDto> getUserById(Integer id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                            .path("search")
                            .queryParam("id", id)
                            .build())
                .retrieve()
                .bodyToMono(SearchUserProfileQueryResultDto.class);
    }
}
