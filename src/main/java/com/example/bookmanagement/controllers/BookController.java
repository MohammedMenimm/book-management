package com.example.bookmanagement.controllers;

import com.example.bookmanagement.entities.Book;
import com.example.bookmanagement.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        return bookService.updateBook(id, bookDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        return bookService.deleteBook(id);
    }

    // Search books by title
    @GetMapping("/search/title")
    public List<Book> searchBooksByTitle(@RequestParam("title") String title) {
        return bookService.searchBooksByTitle(title);
    }

    // Search books by author
    @GetMapping("/search/author")
    public List<Book> searchBooksByAuthor(@RequestParam("author") String author) {
        return bookService.searchBooksByAuthor(author);
    }

    // Search books by genre
    @GetMapping("/search/genre")
    public List<Book> searchBooksByGenre(@RequestParam("genre") String genre) {
        return bookService.searchBooksByGenre(genre);
    }
}
