import com.miguelcordoba.admservice.dto.AuthorDTO;
import com.miguelcordoba.admservice.helper.AuthorMapper;
import com.miguelcordoba.admservice.persistence.entity.Author;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class AuthorMapperTest {

    @Test
    void testMapToDTO() {
        Author author = new Author(1L, "John", "Doe");

        AuthorDTO authorDTO = AuthorMapper.mapToDTO(author);

        assertThat(authorDTO).isNotNull();
        assertThat(authorDTO.id()).isEqualTo(1L);
        assertThat(authorDTO.firstName()).isEqualTo("John");
        assertThat(authorDTO.lastName()).isEqualTo("Doe");
    }

    @Test
    void testMapToEntity() {
        AuthorDTO authorDTO = new AuthorDTO(1L, "Jane", "Smith");

        Author author = AuthorMapper.mapToEntity(authorDTO);

        assertThat(author).isNotNull();
        assertThat(author.getId()).isEqualTo(1L);
        assertThat(author.getFirstName()).isEqualTo("Jane");
        assertThat(author.getLastName()).isEqualTo("Smith");
    }

    @Test
    void testMapDTOSetToEntitySet() {
        Set<AuthorDTO> authorDTOSet = new HashSet<>();
        authorDTOSet.add(new AuthorDTO(1L, "John", "Doe"));
        authorDTOSet.add(new AuthorDTO(2L, "Jane", "Smith"));

        Set<Author> authors = AuthorMapper.mapDTOSetToEntitySet(authorDTOSet);

        assertThat(authors).isNotNull().hasSize(2);
        assertThat(authors).extracting(Author::getFirstName).containsExactlyInAnyOrder("John", "Jane");
        assertThat(authors).extracting(Author::getLastName).containsExactlyInAnyOrder("Doe", "Smith");
    }

    @Test
    void testMapDTOSetToEntityList() {
        Set<AuthorDTO> authorDTOSet = new HashSet<>();
        authorDTOSet.add(new AuthorDTO(1L, "John", "Doe"));
        authorDTOSet.add(new AuthorDTO(2L, "Jane", "Smith"));

        List<Author> authors = AuthorMapper.mapDTOSetToEntityList(authorDTOSet);

        assertThat(authors).isNotNull().hasSize(2);
        assertThat(authors).extracting(Author::getFirstName).containsExactlyInAnyOrder("John", "Jane");
        assertThat(authors).extracting(Author::getLastName).containsExactlyInAnyOrder("Doe", "Smith");
    }
}
