package com.miguelcordoba.admservice.service;

import com.miguelcordoba.admservice.dto.AuthorDTO;
import com.miguelcordoba.admservice.helper.ADMSMapper;
import com.miguelcordoba.admservice.helper.AuthorMapper;
import com.miguelcordoba.admservice.persistence.entity.Author;
import com.miguelcordoba.admservice.persistence.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Optional<AuthorDTO> getAuthorById(Long id) {
        return authorRepository.findById(id).map(ADMSMapper::mapAuthorToDTO);
    }

    @Override
    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author entityAuthor = ADMSMapper.mapAuthorDTOToEntity(authorDTO);
        Author savedAuthor = authorRepository.save(entityAuthor);
        return ADMSMapper.mapAuthorToDTO(savedAuthor);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(ADMSMapper::mapAuthorsToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Author> updateAuthor(Long id, AuthorDTO author) {
        return authorRepository.findById(id)
                .map(existingAuthor -> {
                    existingAuthor.setName(author.name());
                    return authorRepository.save(existingAuthor);
                })
                .map(AuthorMapper::mapToDTO);
    }

    @Override
    public boolean deleteAuthor(Long id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}