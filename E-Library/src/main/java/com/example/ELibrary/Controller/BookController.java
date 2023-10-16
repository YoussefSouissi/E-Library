package com.example.ELibrary.Controller;

import com.example.ELibrary.Repositories.BookRepository;
import com.example.ELibrary.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/getBooks")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Book> getBookById(@PathVariable Long id) {
        return bookRepository.findById(id);
    }

    @PostMapping("/addBooks")
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("updateBooks/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        updatedBook.setId(id);
        return bookRepository.save(updatedBook);
    }

    @DeleteMapping("deleteBooks/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }
}

