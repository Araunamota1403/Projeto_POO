package com.example.spcapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ApiResponse<T> {
    private Body<T> body;

    @Data
    public static class Body<T> {
        private T rows;
        private String access_token;
    }
}
