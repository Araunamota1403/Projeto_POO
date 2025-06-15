package com.example.spcapi.controllers;

import com.example.spcapi.dtos.DeviceResponseDTO;
import com.example.spcapi.exceptions.InvalidSerialException;
import com.example.spcapi.exceptions.MissingTokenException;
import com.example.spcapi.models.Device;
import com.example.spcapi.services.DeviceService;
import com.example.spcapi.utils.DateUtils;
import com.example.spcapi.utils.SerialValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("/device")
    public ResponseEntity<DeviceResponseDTO> getDevice(
            @RequestParam("serialNumber") String serialNumber,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        if (!SerialValidator.isValid(serialNumber)) {
            throw new InvalidSerialException("Número de série inválido");
        }

        String token = extractToken(authHeader);
        Device device = deviceService.findDevice(serialNumber, token);

        if (device == null) {
            return ResponseEntity.badRequest().body(new DeviceResponseDTO(
                    null, null, "Dispositivo não encontrado"
            ));
        }

        String lastUpdateMessage = "Sem última atualização";
        if (device.getStatus() != null && device.getStatus().getLastMessageTimestamp() != null) {
            long diffInDays = DateUtils.millisecondsToDays(
                    new Date().getTime() - device.getStatus().getLastMessageTimestamp()
            );
            lastUpdateMessage = DateUtils.formatDaysMessage(diffInDays);
        }

        return ResponseEntity.ok(new DeviceResponseDTO(
                device.getSerialNumber(),
                device.getConfig() != null ? device.getConfig().getIccid() : null,
                lastUpdateMessage
        ));
    }

    private String extractToken(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new MissingTokenException("Token não encontrado");
        }
        return authHeader.substring(7);
    }
}