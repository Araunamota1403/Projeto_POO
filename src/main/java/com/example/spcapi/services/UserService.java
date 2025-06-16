package com.example.spcapi.services;

import com.example.spcapi.models.ApiResponse;
import com.example.spcapi.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class UserService {

    @Value("${api.user.auth.url}")
    private String userAuthUrl;

    @Value("${api.token}")
    private String apiToken;

    private final RestTemplate restTemplate;

    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String authenticateUser(String email, String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        ResponseEntity<ApiResponse<String>> response = restTemplate.exchange(
                userAuthUrl,
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<ApiResponse<String>>() {}
        );

        if (response.getBody() == null || response.getBody().getBody() == null) {
            return null;
        }

        return response.getBody().getBody().getAccess_token();
    }
}