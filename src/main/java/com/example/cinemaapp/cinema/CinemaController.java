package com.example.cinemaapp.cinema;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/cinema")
@RequiredArgsConstructor
public class CinemaController {

    private final CinemaService cinemaService;

    @PostMapping
    public Cinema add(@RequestParam String city) {
        return cinemaService.addCinema(city);
    }

    @GetMapping("/all")
    public List<Cinema> getAll() {
        return cinemaService.getAllCinemas();
    }

    @GetMapping
    public Optional<Cinema> get(@RequestParam UUID cinemaId) {
        return cinemaService.getCinemaById(cinemaId);
    }
}