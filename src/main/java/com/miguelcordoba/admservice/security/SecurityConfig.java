package com.miguelcordoba.admservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/h2-console/**").permitAll()  // Allow access to H2 console
                        .anyRequest().authenticated()                 // Secure other endpoints
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**")      // Disable CSRF for H2 console
                )
                .headers(headers -> headers
                        .frameOptions().sameOrigin()                    // Allow frames from the same origin for H2 console
                );

        return http.build();
    }
}
