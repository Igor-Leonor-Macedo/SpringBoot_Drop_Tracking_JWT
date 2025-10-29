package com.SpringBoot.Drop_Tracking_JWT.service;

import com.SpringBoot.Drop_Tracking_JWT.dto.request.UserRequestDto;
import com.SpringBoot.Drop_Tracking_JWT.entity.User;
import com.SpringBoot.Drop_Tracking_JWT.exception.ExistingUserException;
import com.SpringBoot.Drop_Tracking_JWT.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String createUser(UserRequestDto userRequestDto){
        if (userRepository.existsByCpf(userRequestDto.getCpf())){
            throw new ExistingUserException("CPF já cadastrado!");}

        User user = new User();
        user.setCpf(userRequestDto.getCpf());
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        user.setRoles(userRequestDto.getRoles());
        userRepository.save(user);
        return ("Usuário Salvo.");
    }
}
