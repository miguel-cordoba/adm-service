package com.miguelcordoba.admservice.service;

import com.miguelcordoba.admservice.dto.AuthorDTO;
import com.miguelcordoba.admservice.persistence.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Optional<AuthorDTO> getAuthorById(Long id);
    AuthorDTO createAuthor(AuthorDTO author);
    List<Author> getAllAuthors();
    Optional<Author> updateAuthor(Long id, AuthorDTO author);
    boolean deleteAuthor(Long id);
}
