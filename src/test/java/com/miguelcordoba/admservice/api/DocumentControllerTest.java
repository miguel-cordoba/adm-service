package com.miguelcordoba.admservice.api;

import com.miguelcordoba.admservice.dto.DocumentDTO;
import com.miguelcordoba.admservice.service.DocumentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class DocumentControllerTest {

    @Mock
    private DocumentServiceImpl documentService;

    @InjectMocks
    private DocumentController documentController;

    private DocumentDTO documentDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        documentDTO = new DocumentDTO(1L, "Sample Title", "Sample Body", Collections.emptySet(), "Sample References");
    }

    @Test
    void getAllDocuments_ShouldReturnListOfDocuments() {
        when(documentService.getAllDocuments()).thenReturn(List.of(documentDTO));

        ResponseEntity<List<DocumentDTO>> response = documentController.getAllDocuments();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).hasSize(1);
        assertThat(response.getBody().get(0).title()).isEqualTo("Sample Title");
    }

    @Test
    void getAllDocuments_ShouldReturnEmptyList_WhenNoDocumentsExist() {
        when(documentService.getAllDocuments()).thenReturn(Collections.emptyList());

        ResponseEntity<List<DocumentDTO>> response = documentController.getAllDocuments();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEmpty();
    }

    @Test
    void getDocumentById_ShouldReturnDocument_WhenDocumentExists() {
        when(documentService.getDocumentById(anyLong())).thenReturn(Optional.of(documentDTO));

        ResponseEntity<DocumentDTO> response = documentController.getDocumentById(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().title()).isEqualTo("Sample Title");
    }

    @Test
    void getDocumentById_ShouldReturnNotFound_WhenDocumentDoesNotExist() {
        when(documentService.getDocumentById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<DocumentDTO> response = documentController.getDocumentById(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void createDocument_ShouldReturnCreatedDocument() {
        when(documentService.createDocument(any(DocumentDTO.class))).thenReturn(documentDTO);

        ResponseEntity<DocumentDTO> response = documentController.createDocument(documentDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().title()).isEqualTo("Sample Title");
    }

    @Test
    void updateDocument_ShouldReturnUpdatedDocument_WhenDocumentExists() {
        when(documentService.updateDocument(anyLong(), any(DocumentDTO.class))).thenReturn(Optional.of(documentDTO));

        ResponseEntity<DocumentDTO> response = documentController.updateDocument(1L, documentDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().title()).isEqualTo("Sample Title");
    }

    @Test
    void updateDocument_ShouldReturnNotFound_WhenDocumentDoesNotExist() {
        when(documentService.updateDocument(anyLong(), any(DocumentDTO.class))).thenReturn(Optional.empty());

        ResponseEntity<DocumentDTO> response = documentController.updateDocument(1L, documentDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void deleteDocument_ShouldReturnNoContent_WhenDocumentIsDeleted() {
        when(documentService.deleteDocument(anyLong())).thenReturn(true);

        ResponseEntity<Void> response = documentController.deleteDocument(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void deleteDocument_ShouldReturnNotFound_WhenDocumentDoesNotExist() {
        when(documentService.deleteDocument(anyLong())).thenReturn(false);

        ResponseEntity<Void> response = documentController.deleteDocument(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

}

