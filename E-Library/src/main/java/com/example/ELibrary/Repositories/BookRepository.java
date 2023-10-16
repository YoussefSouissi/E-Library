package com.example.ELibrary.Repositories;

import com.example.ELibrary.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}

