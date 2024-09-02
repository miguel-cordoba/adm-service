package com.miguelcordoba.admservice.service;

import com.miguelcordoba.admservice.dto.AuthorDTO;
import com.miguelcordoba.admservice.dto.DocumentDTO;
import com.miguelcordoba.admservice.helper.ADMSMapper;
import com.miguelcordoba.admservice.persistence.entity.Author;
import com.miguelcordoba.admservice.persistence.entity.Document;
import com.miguelcordoba.admservice.persistence.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository, AuthorServiceImpl authorService) {
        this.documentRepository = documentRepository;
    }

    @Override
    public List<DocumentDTO> getAllDocuments() {
        return documentRepository.findAll()
                .stream()
                .map(ADMSMapper::mapDocumentToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DocumentDTO> getDocumentById(Long id) {
        return documentRepository.findById(id).map(ADMSMapper::mapDocumentToDTO);
    }

    @Override
    public DocumentDTO createDocument(DocumentDTO documentDTO) {
        // Convert DTO to entity and save
        Document document = ADMSMapper.mapDocumentDTO2Entity(documentDTO);
        Document savedDocument = documentRepository.save(document);
        // Convert saved entity back to DTO
        return ADMSMapper.mapDocumentToDTO(savedDocument);
    }

    @Override
    public Optional<DocumentDTO> updateDocument(Long id, DocumentDTO documentDTO) {
        return documentRepository.findById(id)
                .map(existingDoc -> {
                    existingDoc.setBody(documentDTO.body());
                    existingDoc.setAuthors(ADMSMapper.mapAuthorsToEntity(documentDTO.authors()));
                    existingDoc.setReferences(documentDTO.references());
                    return documentRepository.save(existingDoc);
                })
                .map(ADMSMapper::mapDocumentToDTO); // Convert back to DTO
    }

    @Override
    public boolean deleteDocument(Long id) {
        if (documentRepository.existsById(id)) {
            documentRepository.deleteById(id);
            return true;
        }

        return false;
    }

}
