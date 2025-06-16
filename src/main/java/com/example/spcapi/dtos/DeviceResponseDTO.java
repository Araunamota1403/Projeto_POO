package com.example.spcapi.dtos;

import lombok.Data;

@Data
public class DeviceResponseDTO {
    private String serialNumber;
    private String iccid;
    private String lastMessageTimestamp;

    public DeviceResponseDTO(String serialNumber, String iccid, String lastMessageTimestamp) {
        this.serialNumber = serialNumber != null ? serialNumber : "Serial não encontrado";
        this.iccid = iccid != null ? iccid : "ICCID não encontrado";
        this.lastMessageTimestamp = lastMessageTimestamp != null ? lastMessageTimestamp : "Sem última atualização";
    }
}