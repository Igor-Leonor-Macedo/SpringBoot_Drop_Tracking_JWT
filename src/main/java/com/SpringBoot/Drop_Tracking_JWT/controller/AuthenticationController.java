package com.SpringBoot.Drop_Tracking_JWT.controller;

import com.SpringBoot.Drop_Tracking_JWT.dto.request.LoginRequestDto;
import com.SpringBoot.Drop_Tracking_JWT.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins ="http://localhost:4200",methods = {RequestMethod.GET,RequestMethod.POST})
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        // Retorna o token como String para o front-end
        String token = authenticationService.login(loginRequestDto);
        return ResponseEntity.ok(token); // Wrap token in a response object
    }

    @PostMapping("authenticateUser")
    public ResponseEntity<?> authenticate(Authentication authentication) {
        String token = authenticationService.authenticate(authentication);
        return ResponseEntity.ok(token);
    }

    @PostMapping("authenticate")
    public ResponseEntity<?> authenticate(@Valid @RequestBody LoginRequestDto loginRequestDTO) {
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getCPF(), loginRequestDTO.getPassword());
        String token = authenticationService.authenticate(authentication);
        return ResponseEntity.ok(token);
    }

    @PostMapping("validate")
    public ResponseEntity<Map<String, Object>> validateToken(@RequestBody Map<String, String> request) {
        String token = request.get("token");

        if (token == null || token.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("valid", false, "message", "Token não fornecido"));
        }

        boolean isValid = authenticationService.validateToken(token);
        Map<String, Object> response = new HashMap<>();

        if (isValid) {
            String username = authenticationService.extractUsername(token);
            List<String> roles = authenticationService.extractRoles(token);

            response.put("valid", true);
            response.put("username", username);
            response.put("roles", roles);
        } else {
            response.put("valid", false);
            response.put("message", "Token inválido ou expirado");
        }

        return ResponseEntity.ok(response);
    }
}
