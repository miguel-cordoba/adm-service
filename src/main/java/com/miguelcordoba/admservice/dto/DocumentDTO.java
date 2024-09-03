package com.miguelcordoba.admservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import java.util.Set;

public record DocumentDTO(
        @NotNull(message = "ID cannot be null")
        Long id,

        @NotBlank(message = "Title cannot be blank")
        @Size(max = 255, message = "Title must be less than 255 characters")
        String title,

        @NotBlank(message = "Body cannot be blank")
        @Size(max = 255, message = "Body must be less than 255 characters")
        String body,

        Set<AuthorDTO> authors,

        @NotBlank(message = "References cannot be blank")
        String references
) {}
