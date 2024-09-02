package com.miguelcordoba.admservice.service;

import com.miguelcordoba.admservice.dto.DocumentDTO;
import com.miguelcordoba.admservice.persistence.entity.Document;

import java.util.List;
import java.util.Optional;

public interface DocumentService {

    List<DocumentDTO> getAllDocuments();

    Optional<DocumentDTO> getDocumentById(Long id);

    DocumentDTO createDocument(DocumentDTO documentDTO);

    Optional<DocumentDTO> updateDocument(Long id, DocumentDTO documentDTO);

    boolean deleteDocument(Long id);
}
