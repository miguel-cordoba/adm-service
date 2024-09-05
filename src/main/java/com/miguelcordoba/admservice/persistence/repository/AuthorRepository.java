package com.miguelcordoba.admservice.persistence.repository;

import com.miguelcordoba.admservice.persistence.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Author a WHERE :documentId MEMBER OF a.documents")
    void deleteByDocumentId(Long documentId);
}
