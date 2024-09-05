package com.miguelcordoba.admservice.service;

import com.miguelcordoba.admservice.dto.AuthorDTO;
import com.miguelcordoba.admservice.dto.DocumentDTO;
import com.miguelcordoba.admservice.helper.AuthorMapper;
import com.miguelcordoba.admservice.persistence.entity.Document;
import com.miguelcordoba.admservice.persistence.repository.DocumentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class DocumentServiceImplTest {

    @Mock
    private DocumentRepository documentRepository;

    @InjectMocks
    private DocumentServiceImpl documentService;

    private Document document;
    private DocumentDTO documentDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Sample data
        Set<AuthorDTO> authorDTOs = new HashSet<>();
        authorDTOs.add(new AuthorDTO(1L, "John", "Doe"));

        document = new Document(1L, "Title", "Body", AuthorMapper.mapDTOSetToEntitySet(authorDTOs), "References");
        documentDTO = new DocumentDTO(1L, "Title", "Body", authorDTOs, "References");
    }

    @Test
    void testGetAllDocuments() {
        when(documentRepository.findAll()).thenReturn(List.of(document));

        List<DocumentDTO> documents = documentService.getAllDocuments();

        assertThat(documents).hasSize(1);
        assertThat(documents.get(0).title()).isEqualTo("Title");
    }

    @Test
    void testGetDocumentById() {
        when(documentRepository.findById(anyLong())).thenReturn(Optional.of(document));

        Optional<DocumentDTO> result = documentService.getDocumentById(1L);

        assertThat(result).isPresent();
        assertThat(result.get().title()).isEqualTo("Title");
    }

    @Test
    void testCreateDocument() {
        when(documentRepository.save(any(Document.class))).thenReturn(document);

        DocumentDTO savedDocumentDTO = documentService.createDocument(documentDTO);


        assertThat(savedDocumentDTO).isNotNull();
        assertThat(savedDocumentDTO.title()).isEqualTo("Title");
    }

   // @Test
    void testUpdateDocument() {
        when(documentRepository.findById(anyLong())).thenReturn(Optional.of(document));
        when(documentRepository.save(any(Document.class))).thenReturn(document);

        Optional<DocumentDTO> updatedDocument = documentService.updateDocument(1L, documentDTO);

        assertThat(updatedDocument).isPresent();
        assertThat(updatedDocument.get().body()).isEqualTo("Body");
    }

    @Test
    void testDeleteDocument() {
        when(documentRepository.existsById(anyLong())).thenReturn(true);

        boolean result = documentService.deleteDocument(1L);

        assertThat(result).isTrue();
        verify(documentRepository, times(1)).deleteById(anyLong());
    }
}
