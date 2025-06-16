package com.example.spcapi.services;

import com.example.spcapi.models.Device;
import com.example.spcapi.models.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class DeviceService {

    @Value("${api.device.url}")
    private String deviceApiUrl;

    private final RestTemplate restTemplate;

    public DeviceService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Device findDevice(String serial, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<ApiResponse<Device>> response = restTemplate.exchange(
                deviceApiUrl + "?serialNumber=" + serial,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<ApiResponse<Device>>() {}
        );

        if (response.getBody() == null || response.getBody().getBody() == null) {
            return null;
        }

        return response.getBody().getBody().getRows();
    }
}