package com.miguelcordoba.admservice.helper;

import com.miguelcordoba.admservice.dto.AuthorDTO;
import com.miguelcordoba.admservice.persistence.entity.Author;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AuthorMapper {

    public static AuthorDTO mapToDTO(Author author) {
        return new AuthorDTO(author.getId(), author.getFirstName(), author.getLastName());
    }

    public static Author mapToEntity(AuthorDTO authorDTO) {
        return new Author(authorDTO.id(), authorDTO.firstName(), authorDTO.lastName());
    }

    public static Set<Author> mapDTOSetToEntitySet(Set<AuthorDTO> authorsDTO) {
        return authorsDTO.stream()
                .map(AuthorMapper::mapToEntity)
                .collect(Collectors.toSet());
    }

    public static List<Author> mapDTOSetToEntityList(Set<AuthorDTO> authorDTOSet) {
        Set<Author> authors = mapDTOSetToEntitySet(authorDTOSet);
        return new ArrayList<>(authors);
    }
}



