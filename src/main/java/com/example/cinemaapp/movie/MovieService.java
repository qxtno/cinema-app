package com.example.cinemaapp.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public void saveAllMovies(String[] names) {
        for (String name : names) {
            movieRepository.save(new Movie(name));
        }
    }

    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(UUID id) {
        return movieRepository.findById(id);
    }

    public void delete(UUID movieId) {
        var movie = movieRepository.findById(movieId);
        if (movie.isPresent()) {
            movieRepository.delete(movie.get());
        }
    }
}