package com.SpringBoot.Drop_Tracking_JWT.controller;

import com.SpringBoot.Drop_Tracking_JWT.dto.request.UserRequestDto;
import com.SpringBoot.Drop_Tracking_JWT.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT}) // Aplica CORS para todos os métodos desse controlador
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> SaveUser(@Valid @RequestBody UserRequestDto userRequestDto){
        String register = userService.createUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(register);
    }

    @PutMapping("/update")
    public ResponseEntity<String> UpdateUser(@Valid @RequestBody UserRequestDto userRequestDto){
        String update = userService.updateUserPassword(userRequestDto);
        return ResponseEntity.status(HttpStatus.UPGRADE_REQUIRED).body(update);
    }
}
