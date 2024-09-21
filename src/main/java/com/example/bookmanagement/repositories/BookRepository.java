package com.example.bookmanagement.repositories;

import com.example.bookmanagement.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Search by title
    List<Book> findByTitleContainingIgnoreCase(String title);

    // Search by author
    List<Book> findByAuthorContainingIgnoreCase(String author);

    // Search by genre (using a query with JPQL)
    @Query("SELECT b FROM Book b JOIN b.genres g WHERE g.name = :genreName")
    List<Book> findByGenre(@Param("genreName") String genreName);

    // Filter by published year
    List<Book> findByPublishedYear(int publishedYear);

    // Filter by both published year and genre
    @Query("SELECT b FROM Book b JOIN b.genres g WHERE g.name = :genreName AND b.publishedYear = :publishedYear")
    List<Book> findByGenreAndPublishedYear(@Param("genreName") String genreName, @Param("publishedYear") int publishedYear);
}
