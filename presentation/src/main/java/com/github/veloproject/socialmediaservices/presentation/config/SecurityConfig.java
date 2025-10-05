package com.github.veloproject.socialmediaservices.presentation.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import java.security.interfaces.RSAPublicKey;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Value("${jwt.public.key}")
    private RSAPublicKey publicKey;

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**")
                        .permitAll()

                        .requestMatchers(HttpMethod.POST,
                                "/api/social_media/posts/v1/publish",
                                "/api/social_media/likes/v1/like",
                                "/api/social_media/comments/v1/publish",
                                "/api/social_media/communities/v1/join",
                                "/api/social_media/followers/v1/follow"
                        ).permitAll()

                        .requestMatchers(HttpMethod.GET,
                                "/api/social_media/posts/v1/user/*",
                                "/api/social_media/posts/v1/post",
                                "/api/social_media/likes/v1/*",
                                "/api/social_media/communities/v1/community",
                                "/api/social_media/communities/v1/members/*",
                                "/api/social_media/feeds/v1/feed/*",
                                "/api/social_media/following/v1/*",
                                "/api/social_media/followers/v1/*"
                        ).permitAll()

                        .requestMatchers(HttpMethod.DELETE,
                                "/api/social_media/posts/v1/delete",
                                "/api/social_media/likes/v1/unlike",
                                "/api/social_media/comments/v1/delete",
                                "/api/social_media/communities/v1/delete",
                                "/api/social_media/communities/v1/leave",
                                "/api/social_media/followers/v1/unfollow"
                        ).permitAll().anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(Customizer.withDefaults()))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder
                .withPublicKey(this.publicKey)
                .build();
    }
}
