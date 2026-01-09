package com.SpringSecurity.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * This bean prevents Spring Boot from auto-creating a default user
 * (and printing a generated password) during startup.
 * Since we authenticate using JWT (stateless), we don't need a built-in
 * username/password user store for Spring Security itself.
 */
@Configuration
public class UserDetailsServiceConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        // JWT handles authentication; Spring doesn't need to load users here.
        // Returning an implementation that always fails avoids default user creation.
        return username -> {
            throw new UnsupportedOperationException(
                    "UserDetailsService is not used. JWT authentication is configured."
            );
        };
    }
}
