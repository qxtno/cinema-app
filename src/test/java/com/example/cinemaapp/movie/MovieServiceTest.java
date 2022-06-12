package com.example.cinemaapp.movie;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @Test
    void shouldReturnMovieById() {
        UUID id = UUID.randomUUID();
        Movie movie = new Movie(id, "Name", null);

        when(movieRepository.findById(id)).thenReturn(Optional.of(movie));
        Optional<Movie> result = movieService.getMovieById(id);

        assertEquals(movie, result.get());
    }

    @Test
    void shouldReturnAllCinemas() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        Movie movie1 = new Movie(id1, "Name", null);
        Movie movie2 = new Movie(id2, "Name 1", null);

        when(movieRepository.findAll()).thenReturn(List.of(movie1, movie2));
        List<Movie> result = movieService.getAll();

        assertEquals(List.of(movie1, movie2), result);
    }
}