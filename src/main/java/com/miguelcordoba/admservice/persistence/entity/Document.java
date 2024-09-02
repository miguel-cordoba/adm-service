package com.miguelcordoba.admservice.persistence.entity;

import com.miguelcordoba.admservice.dto.AuthorDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Entity
@Table(name="documents")
@Getter
@AllArgsConstructor
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "body")
    private String body;
    @OneToMany
    private Set<Author> authors;
    @Column(name = "references")
    private String references;

    public Document() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public void setReferences(String references) {
        this.references = references;
    }


}

