package com.smartbiz.smartbiz_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/v1/smartbiz/auth/register", "/api/v1/smartbiz/auth/login").permitAll()
//                        .anyRequest().authenticated()
//                );
//        return http.build();
//    }
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Disable CSRF (Cross-Site Request Forgery) protection, common for stateless APIs.
                .csrf(csrf -> csrf.disable())

                // 2. Configure authorization rules
                .authorizeHttpRequests(auth -> auth
                        // Allow all requests to the authentication endpoints (login, register)
                        .requestMatchers("/api/v1/smartbiz/auth/**").permitAll()
                        // Require authentication for all other requests
                        .anyRequest().authenticated()
                )

                // 3. Configure session management to be stateless.
                // Since we are using JWTs, the server does not need to maintain a session.
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
