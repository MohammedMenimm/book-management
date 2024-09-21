package com.example.bookmanagement.services;

import com.example.bookmanagement.entities.Genre;
import com.example.bookmanagement.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Optional<Genre> getGenreById(Long id) {
        return genreRepository.findById(id);
    }

    public Genre saveGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }

    public Genre updateGenre(Long id, Genre updatedGenre) {
        return genreRepository.findById(id).map(genre -> {
            genre.setName(updatedGenre.getName());
            return genreRepository.save(genre);
        }).orElseThrow(() -> new RuntimeException("Genre not found"));
    }
}
