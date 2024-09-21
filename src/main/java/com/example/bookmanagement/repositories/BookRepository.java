package com.example.bookmanagement.repositories;

import com.example.bookmanagement.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
