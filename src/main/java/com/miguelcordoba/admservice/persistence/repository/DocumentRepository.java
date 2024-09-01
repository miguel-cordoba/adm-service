package com.miguelcordoba.admservice.persistence.repository;

import com.miguelcordoba.admservice.persistence.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Integer> {
}
