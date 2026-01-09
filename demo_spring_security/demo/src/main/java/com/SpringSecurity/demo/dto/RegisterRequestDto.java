package com.SpringSecurity.demo.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Set;

public record RegisterRequestDto(
        @NotBlank @Size(min = 3, max = 50) String userName,
        @NotBlank @Size(min = 8, max = 100) String password,
        @Email @NotBlank String email,
        Set<String> roles
) {}
