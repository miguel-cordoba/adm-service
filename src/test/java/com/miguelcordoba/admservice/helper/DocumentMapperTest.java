package com.miguelcordoba.admservice.helper;

import com.miguelcordoba.admservice.dto.AuthorDTO;
import com.miguelcordoba.admservice.dto.DocumentDTO;
import com.miguelcordoba.admservice.persistence.entity.Author;
import com.miguelcordoba.admservice.persistence.entity.Document;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DocumentMapperTest {
    @Test
    void testMapToDTO() {
        Set<Author> authors = new HashSet<>();
        authors.add(new Author(1L, "John", "Doe"));
        authors.add(new Author(2L, "Jane", "Smith"));

        Document document = new Document(1L, "Title", "Body", authors, "References");

        DocumentDTO documentDTO = DocumentMapper.mapToDTO(document);
        // Act and Assert
        // Collect first names and assert
        Set<String> firstNames = documentDTO.authors().stream()
                .map(AuthorDTO::firstName)
                .collect(Collectors.toSet());
        Set<String> lastNames = documentDTO.authors().stream()
                .map(AuthorDTO::lastName)
                .collect(Collectors.toSet());



        assertThat(documentDTO).isNotNull();
        assertThat(documentDTO.id()).isEqualTo(1L);
        assertThat(documentDTO.title()).isEqualTo("Title");
        assertThat(documentDTO.body()).isEqualTo("Body");
        assertThat(documentDTO.authors().size()).isEqualTo(2);
        assertTrue(firstNames.containsAll(Set.of("Jane", "John")));
        assertTrue(lastNames.containsAll(Set.of("Doe", "Smith")));
        assertThat(documentDTO.references()).isEqualTo("References");
    }

    @Test
    void testMapToEntity() {
        Set<AuthorDTO> authorDTOs = new HashSet<>();
        authorDTOs.add(new AuthorDTO(1L, "Jane", "Smith"));
        authorDTOs.add(new AuthorDTO(2L, "John", "Doe"));

        DocumentDTO documentDTO = new DocumentDTO(1L, "Title", "Body", authorDTOs, "References");

        Document document = DocumentMapper.mapToEntity(documentDTO);

        Set<String> firstNames = document.getAuthors().stream()
                .map(Author::getFirstName)
                .collect(Collectors.toSet());
        Set<String> lastNames = document.getAuthors().stream()
                .map(Author::getLastName)
                .collect(Collectors.toSet());


        assertThat(document).isNotNull();
        assertThat(document.getId()).isEqualTo(1L);
        assertThat(document.getTitle()).isEqualTo("Title");
        assertThat(document.getBody()).isEqualTo("Body");
        assertThat(document.getAuthors().size()).isEqualTo(2);
        assertTrue(firstNames.containsAll(Set.of("Jane", "John")));
        assertTrue(lastNames.containsAll(Set.of("Doe", "Smith")));
        assertThat(document.getReferenceText()).isEqualTo("References");
    }
}

