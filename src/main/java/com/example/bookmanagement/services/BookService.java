package com.example.bookmanagement.services;

import com.example.bookmanagement.entities.Book;
import com.example.bookmanagement.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Existing methods ...

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

    // Filter books by published year
    public List<Book> filterBooksByPublishedYear(int year) {
        return bookRepository.findByPublishedYear(year);
    }

    // Filter books by genre and published year
    public List<Book> filterBooksByGenreAndYear(String genre, int year) {
        return bookRepository.findByGenreAndPublishedYear(genre, year);
    }
}
