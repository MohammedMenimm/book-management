package com.example.bookmanagement.repositories;

import com.example.bookmanagement.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    // Custom query methods can be added here if needed
}
