package com.miguelcordoba.admservice.kafka;

import com.miguelcordoba.admservice.persistence.repository.AuthorRepository;
import com.miguelcordoba.admservice.persistence.repository.DocumentRepository;
import com.miguelcordoba.admservice.service.AuthorServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthorConsumer {

    private final AuthorRepository authorRepository;
    private final DocumentRepository documentRepository;



    @Autowired
    public AuthorConsumer(AuthorRepository authorRepository, DocumentRepository documentRepository) {
        this.authorRepository = authorRepository;
        this.documentRepository = documentRepository;
    }

    @KafkaListener(topics = "authors-topic", groupId = "adms-group")
    public void consume(ConsumerRecord<String, Object> record) {

        Long authorId = (Long) record.value();

        authorRepository.deleteById(authorId);
        documentRepository.deleteByAuthorId(authorId);
        log.debug("Author with id {} will be deleted", authorId);
    }

    @KafkaListener(topics = "documents-topic", groupId = "adms-group")
    public void listenDocumentsTopic(String message) {
        // Process the message for documents
        System.out.println("Received message from documents-topic: " + message);
        // Add your logic here
    }
}
