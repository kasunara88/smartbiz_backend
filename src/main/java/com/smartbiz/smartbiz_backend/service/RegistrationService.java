package com.smartbiz.smartbiz_backend.service;

import com.smartbiz.smartbiz_backend.dto.RegistrationDto;
import org.springframework.stereotype.Service;

@Service
public interface RegistrationService {
    RegistrationDto userRegistration (RegistrationDto registrationDto);
    String userLogin (RegistrationDto login);
}
