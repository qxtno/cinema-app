package com.example.cinemaapp.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public void add(@RequestParam String[] names) {
        movieService.saveAllMovies(names);
    }

    @GetMapping("/all")
    public List<Movie> getAll() {
        return movieService.getAll();
    }

    @GetMapping
    public Optional<Movie> get(@RequestParam UUID id) {
        return movieService.getMovieById(id);
    }

    @DeleteMapping
    public void delete(@RequestParam UUID id) {
        movieService.delete(id);
    }
}