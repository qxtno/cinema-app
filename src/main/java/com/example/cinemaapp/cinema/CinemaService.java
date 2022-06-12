package com.example.cinemaapp.cinema;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CinemaService {

    private final CinemaRepository cinemaRepository;

    public Cinema addCinema(String city) {
        return cinemaRepository.save(new Cinema(city));
    }

    public List<Cinema> getAllCinemas() {
        return cinemaRepository.findAll();
    }

    public Optional<Cinema> getCinemaById(UUID cinemaId) {
        return cinemaRepository.findById(cinemaId);
    }
}