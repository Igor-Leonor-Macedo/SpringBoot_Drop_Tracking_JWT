package com.SpringBoot.Drop_Tracking_JWT.service;

import com.SpringBoot.Drop_Tracking_JWT.dto.request.DeviceRequestDto;
import com.SpringBoot.Drop_Tracking_JWT.dto.response.DeviceResponseDto;
import com.SpringBoot.Drop_Tracking_JWT.entity.Connection;
import com.SpringBoot.Drop_Tracking_JWT.entity.Device;
import com.SpringBoot.Drop_Tracking_JWT.exception.DataCannotBeNull;
import com.SpringBoot.Drop_Tracking_JWT.exception.DeviceAlreadyRegister;
import com.SpringBoot.Drop_Tracking_JWT.exception.DeviceNotConnected;
import com.SpringBoot.Drop_Tracking_JWT.repository.ConnectionRepository;
import com.SpringBoot.Drop_Tracking_JWT.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final ConnectionRepository connectionRepository;

    public DeviceService(DeviceRepository deviceRepository, ConnectionRepository connectionRepository) {
        this.deviceRepository = deviceRepository;
        this.connectionRepository = connectionRepository;
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

    public boolean connectDevice(String macAddress){
        if (macAddress == null || macAddress.isEmpty()){
            throw new DataCannotBeNull("O macAddress é obrigatório!");
        }
        Optional<Device> connect = deviceRepository.findByMacAddress(macAddress);

        if(!connect.isPresent()){
            throw new DeviceNotConnected("Dispositivo não encontrado!");
        }

        Device device = connect.get();
        device.setConnected(true);
        deviceRepository.save(device);

        Connection  connection = new Connection();
        connection.setFirstSeen(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        connection.setDevice(connect.get());
        connectionRepository.save(connection);
        return true;
    }

    public boolean disconnectDevice(String macAddress){
        if (macAddress == null || macAddress.isEmpty()){
            throw new DataCannotBeNull("O macAddress é obrigatório!");
        }
        Optional<Device> deviceOpt = deviceRepository.findByMacAddress(macAddress);
        if(!deviceOpt.isPresent()){
            throw new DeviceNotConnected("Dispositivo não encontrado!");
        }
        Device device = deviceOpt.get();
        device.setConnected(false);
        device.setLastSeenAt(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        deviceRepository.save(device);

        Optional<Connection> lastConnectionOpt = connectionRepository.findTopByDeviceIdAndLastSeenIsNullOrderByFirstSeenDesc(device.getId());
        if(!lastConnectionOpt.isPresent()){
            throw new DeviceNotConnected("Nenhuma conexão ativa encontrada para o dispositivo ID=" + device.getId());
        }
        Connection last = lastConnectionOpt.get();
        last.setLastSeen(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        connectionRepository.save(last);
        return true;
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
