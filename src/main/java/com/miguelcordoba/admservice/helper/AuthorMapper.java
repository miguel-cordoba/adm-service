package com.miguelcordoba.admservice.helper;

import com.miguelcordoba.admservice.dto.AuthorDTO;
import com.miguelcordoba.admservice.persistence.entity.Author;

public class AuthorMapper {


    public static AuthorDTO mapToDTO(Author author) {
        return new AuthorDTO(author.getId(), author.getFirstName(), author.getLastName());    }

    public static Author mapToEntity(AuthorDTO authorDTO) {
        return new Author(authorDTO.id(), authorDTO.firstName(), authorDTO.lastName());
    }

}
