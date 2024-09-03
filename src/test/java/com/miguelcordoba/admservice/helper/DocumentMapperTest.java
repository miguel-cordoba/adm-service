package com.miguelcordoba.admservice.helper;

import com.miguelcordoba.admservice.dto.AuthorDTO;
import com.miguelcordoba.admservice.dto.DocumentDTO;
import com.miguelcordoba.admservice.persistence.entity.Author;
import com.miguelcordoba.admservice.persistence.entity.Document;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DocumentMapperTest {
    @Test
    void testMapToDTO() {
        Set<Author> authors = new HashSet<>();
        authors.add(new Author(1L, "John", "Doe"));
        Document document = new Document(1L, "Title", "Body", authors, "References");

        DocumentDTO documentDTO = DocumentMapper.mapToDTO(document);

        assertThat(documentDTO).isNotNull();
        assertThat(documentDTO.id()).isEqualTo(1L);
        assertThat(documentDTO.title()).isEqualTo("Title");
        assertThat(documentDTO.body()).isEqualTo("Body");
        assertThat(documentDTO.authors().size()).isEqualTo(1);
        assertThat(documentDTO.authors()).extracting(AuthorDTO::firstName).containsExactly("John");
        assertThat(documentDTO.authors()).extracting(AuthorDTO::lastName).containsExactly("Doe");
        assertThat(documentDTO.references()).isEqualTo("References");
    }

    @Test
    void testMapToEntity() {
        Set<AuthorDTO> authorDTOs = new HashSet<>();
        authorDTOs.add(new AuthorDTO(1L, "Jane", "Smith"));
        authorDTOs.add(new AuthorDTO(2L, "John", "Doe"));

        DocumentDTO documentDTO = new DocumentDTO(1L, "Title", "Body", authorDTOs, "References");

        Document document = DocumentMapper.mapToEntity(documentDTO);

        assertThat(document).isNotNull();
        assertThat(document.getId()).isEqualTo(1L);
        assertThat(document.getTitle()).isEqualTo("Title");
        assertThat(document.getBody()).isEqualTo("Body");
        assertThat(document.getAuthors().size()).isEqualTo(2);
        assertThat(document.getAuthors()).extracting(Author::getFirstName).containsExactly("Jane");
        assertThat(document.getAuthors()).extracting(Author::getLastName).containsExactly("Smith");
        assertThat(document.getReferences()).isEqualTo("References");
    }
}

