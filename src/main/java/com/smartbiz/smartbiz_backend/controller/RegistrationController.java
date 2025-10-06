package com.smartbiz.smartbiz_backend.controller;

import com.smartbiz.smartbiz_backend.dto.JWTResponseDto;
import com.smartbiz.smartbiz_backend.dto.RegistrationDto;
import com.smartbiz.smartbiz_backend.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/smartbiz")
@CrossOrigin
public class RegistrationController {
   final private RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService){
        this.registrationService = registrationService;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<RegistrationDto> userRegistration(@RequestBody RegistrationDto registrationDto){
        RegistrationDto registration = registrationService.userRegistration(registrationDto);
        return new ResponseEntity<>(registration, HttpStatus.CREATED);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> userLogin(@RequestBody RegistrationDto login){
        try{
            String token = registrationService.userLogin(login);
            return ResponseEntity.ok(new JWTResponseDto(token));
        }catch(RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }



    }
}
