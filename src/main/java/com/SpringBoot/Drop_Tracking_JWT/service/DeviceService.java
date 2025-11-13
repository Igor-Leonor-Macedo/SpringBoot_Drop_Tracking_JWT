package com.SpringBoot.Drop_Tracking_JWT.service;

import com.SpringBoot.Drop_Tracking_JWT.dto.request.DeviceRequestDto;
import com.SpringBoot.Drop_Tracking_JWT.dto.response.DeviceResponseDto;
import com.SpringBoot.Drop_Tracking_JWT.entity.Device;
import com.SpringBoot.Drop_Tracking_JWT.exception.DataCannotBeNull;
import com.SpringBoot.Drop_Tracking_JWT.exception.DeviceAlreadyRegister;
import com.SpringBoot.Drop_Tracking_JWT.exception.DeviceNotConnected;
import com.SpringBoot.Drop_Tracking_JWT.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public List<DeviceResponseDto>findAvailableDevices(){
        List<Device> availableDevices = deviceRepository.findByConnected(false);

        if(availableDevices.isEmpty()){
            throw new DeviceNotConnected("Não há dispositivos disponíveis!");
        }
        return availableDevices.stream()
                .map(this::toDeviceResponseDto)
                .collect(Collectors.toList());
    }

    public String registerDevice(DeviceRequestDto deviceRequestDto){
        if(deviceRequestDto.getMacAddress() == null || deviceRequestDto.getMacAddress().isEmpty()){
            throw new DataCannotBeNull("O macAddress é obrigatório!");
        }
        if(deviceRepository.findDeviceByMacAddress(deviceRequestDto.getMacAddress())){
            throw new DeviceAlreadyRegister("Dispositivo já cadastrado!");
        }
        Device newDevice = new Device(
            deviceRequestDto.getMacAddress(),
            deviceRequestDto.getIpAddress(),
            deviceRequestDto.getDeviceName(),
            LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)
        );
        deviceRepository.save(newDevice);

        return ("Dispositivo registrado com sucesso!");
    }

    private DeviceResponseDto toDeviceResponseDto(Device device) {
        DeviceResponseDto dto = new DeviceResponseDto();
        dto.setId(device.getId());
        dto.setMacAddress(device.getMacAddress());
        dto.setIpAddress(device.getIpAddress());
        dto.setDeviceName(device.getDeviceName());
        dto.setConnected(device.isConnected());
        dto.setFirstSeenAt(device.getFirstSeenAt());
        dto.setLastSeenAt(device.getLastSeenAt());
        return dto;
    }
}
