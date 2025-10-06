package com.smartbiz.smartbiz_backend.repository;

import com.smartbiz.smartbiz_backend.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface RegistrationRepo extends JpaRepository<Registration,Integer> {
    Optional<Registration> findByEmail(String email);
}
