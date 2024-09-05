package com.miguelcordoba.admservice.persistence.repository;

import com.miguelcordoba.admservice.persistence.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    // Custom query to delete documents associated with a specific author
    @Transactional
    @Modifying
    @Query("DELETE FROM Document d WHERE EXISTS (SELECT 1 FROM d.authors a WHERE a.id = :authorId)")
    void deleteByAuthorId(Long authorId);;
}
