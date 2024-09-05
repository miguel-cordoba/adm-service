package com.miguelcordoba.admservice.service;

import com.miguelcordoba.admservice.dto.DocumentDTO;
import com.miguelcordoba.admservice.helper.AuthorMapper;
import com.miguelcordoba.admservice.helper.DocumentMapper;
import com.miguelcordoba.admservice.kafka.DocumentProducer;
import com.miguelcordoba.admservice.persistence.entity.Document;
import com.miguelcordoba.admservice.persistence.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentProducer documentProducer;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository, DocumentProducer documentProducer) {
        this.documentRepository = documentRepository;
        this.documentProducer = documentProducer;
    }

    @Override
    public List<DocumentDTO> getAllDocuments() {
        return documentRepository.findAll()
                .stream()
                .map(DocumentMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DocumentDTO> getDocumentById(Long id) {
        return documentRepository.findById(id).map(DocumentMapper::mapToDTO);
    }

    @Override
    public DocumentDTO createDocument(DocumentDTO documentDTO) {
        // Convert DTO to entity and save
        Document document = DocumentMapper.mapToEntity(documentDTO);
        Document savedDocument = documentRepository.save(document);
        // Convert saved entity back to DTO
        return DocumentMapper.mapToDTO(savedDocument);
    }

    @Override
    public Optional<DocumentDTO> updateDocument(Long id, DocumentDTO documentDTO) {
        Optional<DocumentDTO> updatedDocument =  documentRepository.findById(id)
                .map(existingDoc -> {
                    existingDoc.setBody(documentDTO.body());
                    existingDoc.setAuthors(AuthorMapper.mapDTOSetToEntitySet(documentDTO.authors()));
                    existingDoc.setReferenceText(documentDTO.references());
                    return documentRepository.save(existingDoc);
                })
                .map(DocumentMapper::mapToDTO);
        documentProducer.sendMessage(documentDTO.id().toString());

        return updatedDocument;
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
