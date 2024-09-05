package com.miguelcordoba.admservice.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Entity
@Table(name = "documents")
@Getter
@AllArgsConstructor
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  //We use long for scalability, data volume might increase exponentially in the future
    @Column(name = "title")
    private String title;
    @Column(name = "body")
    private String body;
    @ManyToMany
    @JoinTable(
            name = "documents_authors",
            joinColumns = @JoinColumn(name = "document_id"),
            inverseJoinColumns = @JoinColumn(name = "authors_id")
    )
    private Set<Author> authors;
    @Column(name = "reference_text")
    private String referenceText;

    public Document() { //we need both constructors for JPA and our custom entity mapping
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

    public void setReferenceText(String referenceText) {
        this.referenceText = referenceText;
    }


}

