package com.miguelcordoba.admservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AuthorDTO(
        @NotNull(message = "ID cannot be null")
        Long id,

        @NotBlank(message = "First name cannot be blank")
        @Size(max = 255, message = "First name must be less than 250 characters")
        String firstName,

        @NotBlank(message = "Last name cannot be blank")
        @Size(max = 255, message = "Last name must be less than 50 characters")
        String lastName
) {}
