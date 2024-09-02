package com.miguelcordoba.admservice.dto;

import lombok.Data;

import java.util.Set;


public record DocumentDTO(
        Long id,
        String title,
        String body,
        Set<AuthorDTO> authors,
        String references
) {
}