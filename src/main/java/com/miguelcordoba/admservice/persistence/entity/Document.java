package com.miguelcordoba.admservice.persistence.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name="documents")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "body")
    private String body;
    @OneToMany
    private Set<Author> authors;
    @Column(name = "references")
    private String references;

}

