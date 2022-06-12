package com.example.cinemaapp.screening;

import com.example.cinemaapp.room.Room;
import com.example.cinemaapp.screening.dto.ScreeningDto;
import com.example.cinemaapp.screening.response.ScreeningResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/screening")
@RequiredArgsConstructor
public class ScreeningController {

    private final ScreeningService screeningService;

    @PostMapping
    public Screening add(@RequestBody ScreeningDto screening) {
        return screeningService.addScreening(screening);
    }

    @DeleteMapping
    public void delete(@RequestParam UUID id) {
        screeningService.deleteScreening(id);
    }

    @GetMapping("/all")
    public List<Screening> getAll() {
        return screeningService.getAllScreenings();
    }

    @GetMapping("/movie/all")
    public List<Screening> getAllByMovieId(@RequestParam UUID movieId) {
        return screeningService.getAllScreenings(movieId);
    }

    @GetMapping("/movie/seats")
    public List<Room.Seat> getSeats(@RequestParam UUID screeningId) {
        return screeningService.getSeats(screeningId);
    }

    @PostMapping("/reserve")
    public ResponseEntity<ScreeningResponseEntity> reserve(
            @RequestParam UUID screeningId,
            @RequestBody List<Room.SeatLocation> seatLocations) {
        return screeningService.reservation(screeningId, seatLocations, false);
    }

    @PostMapping("/reserve/cancel")
    public ResponseEntity<ScreeningResponseEntity> cancel(
            @RequestParam UUID screeningId,
            @RequestBody List<Room.SeatLocation> seatLocations) {
        return screeningService.reservation(screeningId, seatLocations, true);
    }
}
