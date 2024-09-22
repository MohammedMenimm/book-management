package com.example.bookmanagement.services;

import com.example.bookmanagement.entities.Book;
import com.example.bookmanagement.entities.Genre;
import com.example.bookmanagement.repositories.BookRepository;
import com.example.bookmanagement.repositories.GenreRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Transactional
    public Book saveBook(Book book) {
        // Persist genres if not already existing
        List<Genre> persistedGenres = book.getGenres().stream()
                .map(genre -> genreRepository.findById(genre.getId())
                        .orElseGet(() -> genreRepository.save(genre)))
                .collect(Collectors.toList());

        book.setGenres(persistedGenres);
        return bookRepository.save(book);
    }

    // Get all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Create a new book
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    // Get book by ID
    public ResponseEntity<Book> getBookById(Long id) {
        return bookRepository.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElse(ResponseEntity.notFound().build());
    }

    // Update a book
    public ResponseEntity<Book> updateBook(Long id, Book bookDetails) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(bookDetails.getTitle());
                    book.setAuthor(bookDetails.getAuthor());
                    book.setIsbn(bookDetails.getIsbn());

                    // Update genres
                    List<Genre> updatedGenres = bookDetails.getGenres().stream()
                            .map(genre -> genreRepository.findById(genre.getId())
                                    .orElseThrow(() -> new RuntimeException("Genre not found: " + genre.getId())))
                            .collect(Collectors.toList());

                    book.setGenres(updatedGenres);

                    Book updatedBook = bookRepository.save(book);
                    return ResponseEntity.ok(updatedBook);
                }).orElse(ResponseEntity.notFound().build());
    }

    // Delete a book
    public ResponseEntity<Void> deleteBook(Long id) {
        return bookRepository.findById(id)
                .map(book -> {
                    bookRepository.delete(book);
                    return ResponseEntity.ok().<Void>build();
                }).orElse(ResponseEntity.notFound().build());
    }

    // Search books by title
    public List<Book> searchBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    // Search books by author
    public List<Book> searchBooksByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }

    // Search books by genre
    public List<Book> searchBooksByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }
}
