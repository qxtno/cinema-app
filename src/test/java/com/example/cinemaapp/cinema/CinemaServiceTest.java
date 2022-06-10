package com.example.cinemaapp.cinema;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CinemaServiceTest {

    @Mock
    private CinemaRepository cinemaRepository;

    @InjectMocks
    private CinemaService cinemaService;

    @Test
    void shouldSaveCinema() {
        UUID id = UUID.randomUUID();
        Cinema cinema = new Cinema(id, "Lublin", null);

        when(cinemaRepository.save(any())).thenReturn(cinema);
        Cinema result = cinemaService.addCinema("Lublin");

        assertEquals(cinema, result);
    }

    @Test
    void shouldReturnAllCinemas() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        Cinema cinema1 = new Cinema(id1, "Lublin", null);
        Cinema cinema2 = new Cinema(id2, "Warszawa", null);

        when(cinemaRepository.findAll()).thenReturn(List.of(cinema1, cinema2));
        List<Cinema> result = cinemaService.getAllCinemas();

        assertEquals(List.of(cinema1, cinema2), result);
    }

    @Test
    void shouldReturnCinemaById() {
        UUID id = UUID.randomUUID();
        Cinema cinema = new Cinema(id, "Lublin", null);

        when(cinemaRepository.findById(id)).thenReturn(Optional.of(cinema));
        Optional<Cinema> result = cinemaService.getCinemaById(id);

        assertEquals(cinema, result.get());
    }
}