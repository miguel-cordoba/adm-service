package com.miguelcordoba.admservice.api;

import com.miguelcordoba.admservice.dto.DocumentDTO;
import com.miguelcordoba.admservice.service.DocumentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/documents")
@Validated
public class DocumentController {

    private final DocumentServiceImpl documentService;

    @Autowired
    public DocumentController(DocumentServiceImpl documentService) {
        this.documentService = documentService;
    }

    @Operation(summary = "Get all documents")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of documents")
    @GetMapping
    public ResponseEntity<List<DocumentDTO>> getAllDocuments() {
        List<DocumentDTO> documents = documentService.getAllDocuments();
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    @Operation(summary = "Get document by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the document"),
            @ApiResponse(responseCode = "404", description = "Document not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DocumentDTO> getDocumentById(@PathVariable @NotNull Long id) {
        Optional<DocumentDTO> document = documentService.getDocumentById(id);
        return document.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Create a new document")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Document successfully created"),
            @ApiResponse(responseCode = "400", description = "Bad Request. No empty fields allowed")

    })
    @PostMapping
    public ResponseEntity<DocumentDTO> createDocument(@Valid @RequestBody DocumentDTO documentDTO) {
        DocumentDTO savedDocument = documentService.createDocument(documentDTO);
        return new ResponseEntity<>(savedDocument, HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing document")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully updated the document"),
            @ApiResponse(responseCode = "404", description = "Document not found"),
            @ApiResponse(responseCode = "400", description = "Bad Request. No empty fields allowed")
    })
    @PutMapping("/{id}")
    public ResponseEntity<DocumentDTO> updateDocument(@PathVariable @NotNull Long id, @Valid @RequestBody DocumentDTO documentDTO) {
        Optional<DocumentDTO> updatedDocument = documentService.updateDocument(id, documentDTO);
        return updatedDocument.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Delete a document")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Successfully deleted the document"),
            @ApiResponse(responseCode = "404", description = "Document not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable @NotNull Long id) {
        boolean isDeleted = documentService.deleteDocument(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
