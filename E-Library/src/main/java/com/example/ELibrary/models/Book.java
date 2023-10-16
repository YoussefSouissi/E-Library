package com.example.ELibrary.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String author;
    private String language;
    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


}

