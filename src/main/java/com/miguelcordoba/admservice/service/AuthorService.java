package com.miguelcordoba.admservice.service;

import com.miguelcordoba.admservice.dto.AuthorDTO;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Optional<AuthorDTO> getAuthorById(Long id);

    AuthorDTO createAuthor(AuthorDTO author);

    List<AuthorDTO> getAllAuthors();

    Optional<AuthorDTO> updateAuthor(Long id, AuthorDTO author);

    boolean deleteAuthor(Long id);
}
