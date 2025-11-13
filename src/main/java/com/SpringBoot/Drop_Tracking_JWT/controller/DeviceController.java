package com.SpringBoot.Drop_Tracking_JWT.controller;

import com.SpringBoot.Drop_Tracking_JWT.dto.request.DeviceRequestDto;
import com.SpringBoot.Drop_Tracking_JWT.dto.response.DeviceResponseDto;
import com.SpringBoot.Drop_Tracking_JWT.entity.Device;
import com.SpringBoot.Drop_Tracking_JWT.service.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/device")
public class DeviceController {

    private final DeviceService deviceService;
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("/available")
    public ResponseEntity<List<DeviceResponseDto>> getAvailableDevices() {
        return ResponseEntity.ok(deviceService.findAvailableDevices());
    }

    @PostMapping("/register")
    public ResponseEntity<String> postRegisterDevice(@RequestBody DeviceRequestDto deviceRequestDto) {
        return ResponseEntity.ok(deviceService.registerDevice(deviceRequestDto));
    }

    @PostMapping("/connect/{macAddress}")
    public ResponseEntity<Boolean> postConnectDevice(@PathVariable String macAddress) {
        boolean connect = deviceService.connectDevice(macAddress);
        return ResponseEntity.ok(connect);
    }
    @PostMapping("/disconnect/{macAddress}")
    public ResponseEntity<Boolean> postDisconnectDevice(@PathVariable String macAddress) {
        boolean disconnect = deviceService.disconnectDevice(macAddress);
        return ResponseEntity.ok(disconnect);
    }
}
