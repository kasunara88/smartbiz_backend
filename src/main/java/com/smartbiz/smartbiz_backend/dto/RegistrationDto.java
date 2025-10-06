package com.smartbiz.smartbiz_backend.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public RegistrationDto(String email, String password){
        this.email = email;
        this.password = password;
    }
}
