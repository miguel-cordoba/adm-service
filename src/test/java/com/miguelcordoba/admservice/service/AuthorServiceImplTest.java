package com.miguelcordoba.admservice.service;

import com.miguelcordoba.admservice.dto.AuthorDTO;
import com.miguelcordoba.admservice.helper.AuthorMapper;
import com.miguelcordoba.admservice.persistence.entity.Author;
import com.miguelcordoba.admservice.persistence.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class AuthorServiceImplTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    private Author author;
    private AuthorDTO authorDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        author = new Author(1L, "John", "Doe");
        authorDTO = new AuthorDTO(1L, "John", "Doe");
    }

    @Test
    void testGetAuthorById() {
        when(authorRepository.findById(anyLong())).thenReturn(Optional.of(author));

        Optional<AuthorDTO> result = authorService.getAuthorById(1L);

        assertThat(result).isPresent();
        assertThat(result.get().firstName()).isEqualTo("John");
        assertThat(result.get().lastName()).isEqualTo("Doe");
    }

    @Test
    void testGetAuthorById_NotFound() {
        when(authorRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<AuthorDTO> result = authorService.getAuthorById(1L);

        assertThat(result).isNotPresent();
    }

    @Test
    void testCreateAuthor() {
        when(authorRepository.save(any(Author.class))).thenReturn(author);

        AuthorDTO savedAuthorDTO = authorService.createAuthor(authorDTO);

        assertThat(savedAuthorDTO).isNotNull();
        assertThat(savedAuthorDTO.firstName()).isEqualTo("John");
        assertThat(savedAuthorDTO.lastName()).isEqualTo("Doe");
    }

    @Test
    void testGetAllAuthors() {
        when(authorRepository.findAll()).thenReturn(List.of(author));

        List<AuthorDTO> authors = authorService.getAllAuthors();

        assertThat(authors).hasSize(1);
        assertThat(authors.get(0).firstName()).isEqualTo("John");
        assertThat(authors.get(0).lastName()).isEqualTo("Doe");
    }

    @Test
    void testUpdateAuthor() {
        when(authorRepository.findById(anyLong())).thenReturn(Optional.of(author));
        when(authorRepository.save(any(Author.class))).thenReturn(author);

        Optional<AuthorDTO> updatedAuthor = authorService.updateAuthor(1L, authorDTO);

        assertThat(updatedAuthor).isPresent();
        assertThat(updatedAuthor.get().firstName()).isEqualTo("John");
        assertThat(updatedAuthor.get().lastName()).isEqualTo("Doe");
    }

    @Test
    void testUpdateAuthor_NotFound() {

        when(authorRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<AuthorDTO> updatedAuthor = authorService.updateAuthor(1L, authorDTO);

        assertThat(updatedAuthor).isNotPresent();
    }

    @Test
    void testDeleteAuthor() {
        when(authorRepository.existsById(anyLong())).thenReturn(true);

        boolean result = authorService.deleteAuthor(1L);

        assertThat(result).isTrue();
        verify(authorRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void testDeleteAuthor_NotFound() {
        when(authorRepository.existsById(anyLong())).thenReturn(false);

        boolean result = authorService.deleteAuthor(1L);

        assertThat(result).isFalse();
        verify(authorRepository, never()).deleteById(anyLong());
    }
}
