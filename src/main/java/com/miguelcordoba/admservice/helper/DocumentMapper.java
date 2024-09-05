package com.miguelcordoba.admservice.helper;

import com.miguelcordoba.admservice.dto.DocumentDTO;
import com.miguelcordoba.admservice.persistence.entity.Document;

import java.util.stream.Collectors;

public class DocumentMapper {

    //TODO: Unit Tests
    public static DocumentDTO mapToDTO(Document document) {
        return new DocumentDTO(
                document.getId(),
                document.getTitle(),
                document.getBody(),
                document.getAuthors().stream().map(AuthorMapper::mapToDTO).collect(Collectors.toSet()),
                document.getReferenceText()
        );
    }

    public static Document mapToEntity(DocumentDTO documentDTO) {
        return new Document(
                documentDTO.id(),
                documentDTO.title(),
                documentDTO.body(),
                AuthorMapper.mapDTOSetToEntitySet(documentDTO.authors()),
                documentDTO.references()
        );
    }

}
