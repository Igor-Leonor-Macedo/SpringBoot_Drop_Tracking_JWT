package com.SpringBoot.Drop_Tracking_JWT.service;

import com.SpringBoot.Drop_Tracking_JWT.dto.request.LoginRequestDto;
import com.SpringBoot.Drop_Tracking_JWT.entity.User;
import com.SpringBoot.Drop_Tracking_JWT.exception.InvalidCredentialsException;
import com.SpringBoot.Drop_Tracking_JWT.exception.UserNotFoundException;
import com.SpringBoot.Drop_Tracking_JWT.repository.UserRepository;
import com.SpringBoot.Drop_Tracking_JWT.service.jwt.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.List;


@Service
public class AuthenticationService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // Adicionar esta dependência


    public AuthenticationService(JwtService jwtService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByCpf(loginRequestDto.getCPF())
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
        // Verifica se a senha está correta
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Senha incorreta");
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(loginRequestDto.getCPF(), loginRequestDto.getPassword());
        return jwtService.generateToken(authentication, user);
    }

    public String authenticate(Authentication authentication) {
        String password = (String) authentication.getCredentials();

        User user = userRepository.findByCpf(authentication.getName())
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
        // Verifica se a senha está correta
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException("Senha incorreta");
        }
        return jwtService.generateToken(authentication, user);
    }

    public boolean validateToken(String token) {
        return jwtService.isTokenValid(token);
    }

    public String extractUsername(String token) {
        return jwtService.extractUsername(token);
    }

    public List<String> extractRoles(String token) {
        return jwtService.extractRoles(token);
    }
}
