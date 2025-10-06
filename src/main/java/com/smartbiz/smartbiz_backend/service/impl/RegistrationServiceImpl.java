package com.smartbiz.smartbiz_backend.service.impl;

import com.smartbiz.smartbiz_backend.config.JwtUtil;
import com.smartbiz.smartbiz_backend.dto.RegistrationDto;
import com.smartbiz.smartbiz_backend.entity.Registration;
import com.smartbiz.smartbiz_backend.repository.RegistrationRepo;
import com.smartbiz.smartbiz_backend.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    RegistrationRepo registrationRepo;
   final private BCryptPasswordEncoder passwordEncoder;
   final private JwtUtil jwtUtil;

    @Autowired
    public RegistrationServiceImpl(RegistrationRepo registrationRepo, BCryptPasswordEncoder passwordEncoder, JwtUtil jwtUtil){
        this.registrationRepo = registrationRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public RegistrationDto userRegistration(RegistrationDto registrationDto) {
        String hashedPassword = passwordEncoder.encode(registrationDto.getPassword());
        Registration save = registrationRepo.save(new Registration(
                registrationDto.getId(),
                registrationDto.getFirstName(),
                registrationDto.getLastName(),
                registrationDto.getEmail(),
                hashedPassword));

        return  new RegistrationDto(
                save.getId(),
                save.getFirstName(),
                save.getLastName(),
                save.getEmail(),
                null);
    }

    @Override
    public String userLogin(RegistrationDto login) {
        Optional<Registration> byEmail = registrationRepo.findByEmail(login.getEmail());

        if(byEmail.isPresent()){
            Registration registration = byEmail.get();
            if(passwordEncoder.matches(login.getPassword(), registration.getPassword())){
                return jwtUtil.generateToken(login.getEmail());
            }else{
                throw new RuntimeException("Invalid credentials");
            }

        }else{
            throw new RuntimeException("User not found");
        }
    }
}
