package com.miguelcordoba.admservice.persistence.entity;

import jakarta.persistence.*;
@Entity
@Table(name= "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
}
