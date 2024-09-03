package com.miguelcordoba.admservice.api;

import com.miguelcordoba.admservice.dto.AuthorDTO;
import com.miguelcordoba.admservice.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class AuthorControllerTest {

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private AuthorController authorController;

    private AuthorDTO authorDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authorDTO = new AuthorDTO(1L, "John", "Doe");
    }

    @Test
    void getAllAuthors_ShouldReturnListOfAuthorsOrEmpty() {
        when(authorService.getAllAuthors()).thenReturn(List.of(authorDTO));

        ResponseEntity<List<AuthorDTO>> response = authorController.getAllAuthors();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).hasSize(1);
        assertThat(response.getBody().get(0).firstName()).isEqualTo("John");

        when(authorService.getAllAuthors()).thenReturn(List.of());
        ResponseEntity<List<AuthorDTO>> emptyResponse = authorController.getAllAuthors();

        assertThat(emptyResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(emptyResponse.getBody()).isEmpty();
    }

    @Test
    void getAuthorById_ShouldHandleAuthorFoundAndNotFound() {
        when(authorService.getAuthorById(anyLong())).thenReturn(Optional.of(authorDTO));

        ResponseEntity<AuthorDTO> foundResponse = authorController.getAuthorById(1L);

        assertThat(foundResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(foundResponse.getBody()).isNotNull();
        assertThat(foundResponse.getBody().firstName()).isEqualTo("John");

        when(authorService.getAuthorById(anyLong())).thenReturn(Optional.empty());
        ResponseEntity<AuthorDTO> notFoundResponse = authorController.getAuthorById(1L);

        assertThat(notFoundResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void createOrUpdateAuthor_ShouldHandleSuccessAndNotFound() {
        when(authorService.createAuthor(any(AuthorDTO.class))).thenReturn(authorDTO);
        when(authorService.updateAuthor(anyLong(), any(AuthorDTO.class))).thenReturn(Optional.of(authorDTO));

        ResponseEntity<AuthorDTO> createResponse = authorController.createAuthor(authorDTO);

        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(createResponse.getBody()).isNotNull();
        assertThat(createResponse.getBody().firstName()).isEqualTo("John");

        ResponseEntity<AuthorDTO> updateResponse = authorController.updateAuthor(1L, authorDTO);

        assertThat(updateResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(updateResponse.getBody()).isNotNull();
        assertThat(updateResponse.getBody().lastName()).isEqualTo("Doe");

        when(authorService.updateAuthor(anyLong(), any(AuthorDTO.class))).thenReturn(Optional.empty());
        ResponseEntity<AuthorDTO> notFoundUpdateResponse = authorController.updateAuthor(1L, authorDTO);

        assertThat(notFoundUpdateResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void deleteAuthor_ShouldHandleDeletionAndNonExistence() {
        when(authorService.deleteAuthor(anyLong())).thenReturn(true).thenReturn(false);

        ResponseEntity<Void> successfulDelete = authorController.deleteAuthor(1L);
        assertThat(successfulDelete.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        ResponseEntity<Void> failedDelete = authorController.deleteAuthor(1L);
        assertThat(failedDelete.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    //@Test
    void createAuthor_ShouldReturnBadRequest_WhenInvalidData() {
        AuthorDTO invalidAuthorDTO = new AuthorDTO(null, "", "");

        ResponseEntity<AuthorDTO> response = authorController.createAuthor(invalidAuthorDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    //@Test
    void updateAuthor_ShouldReturnBadRequest_WhenInvalidData() {
        AuthorDTO invalidAuthorDTO = new AuthorDTO(null, "", "");

        ResponseEntity<AuthorDTO> response = authorController.updateAuthor(1L, invalidAuthorDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

}
