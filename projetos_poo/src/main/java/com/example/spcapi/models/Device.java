package com.example.spcapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Device {
    @JsonProperty("serialNumber")
    private String serialNumber;

    private DeviceConfig config;
    private DeviceStatus status;

    @Data
    public static class DeviceConfig {
        private String iccid;
    }

    @Data
    public static class DeviceStatus {
        @JsonProperty("lastMessageTimestamp")
        private Long lastMessageTimestamp;
    }
}