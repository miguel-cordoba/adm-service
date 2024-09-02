package com.miguelcordoba.admservice.helper;

import com.miguelcordoba.admservice.dto.AuthorDTO;
import com.miguelcordoba.admservice.dto.DocumentDTO;
import com.miguelcordoba.admservice.persistence.entity.Author;
import com.miguelcordoba.admservice.persistence.entity.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ADMSMapper {


    public static DocumentDTO mapDocumentToDTO(Document document) {
        return new DocumentDTO(
                document.getId(),
                document.getTitle(),
                document.getBody(),
                document.getAuthors().stream().map(ADMSMapper::mapAuthorToDTO).collect(Collectors.toSet()),
                document.getReferences()
        );
    }

    public static Document mapDocumentDTO2Entity(DocumentDTO documentDTO) {
        return new Document(
                documentDTO.id(),
                documentDTO.title(),
                documentDTO.body(),
                mapAuthorsToEntity(documentDTO.authors()),
                documentDTO.references()
        );
    }

    public static Set<Author> mapAuthorsToEntity(Set<AuthorDTO> authorsDTO) {
        return authorsDTO.stream()
                .map(ADMSMapper::mapAuthorDTOToEntity)
                .collect(Collectors.toSet());
    }

    public static List<Author> mapAuthorsToEntityAsList(Set<AuthorDTO> authorsDTO) {
        return new ArrayList<>(mapAuthorsToEntity(authorsDTO));
    }

    public static Author mapAuthorDTOToEntity(AuthorDTO authorDTO) {
        return new Author(authorDTO.id(), authorDTO.firstName(), authorDTO.lastName());
    }

    public static AuthorDTO mapAuthorToDTO(Author author) {
        return new AuthorDTO(author.getId(), author.getFirstName(), author.getLastName());
    }
}
