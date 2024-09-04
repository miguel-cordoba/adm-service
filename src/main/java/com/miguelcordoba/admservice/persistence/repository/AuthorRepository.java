package com.miguelcordoba.admservice.persistence.repository;

import com.miguelcordoba.admservice.persistence.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
