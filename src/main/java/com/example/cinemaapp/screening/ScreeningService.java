package com.example.cinemaapp.screening;

import com.example.cinemaapp.room.Room;
import com.example.cinemaapp.room.RoomService;
import com.example.cinemaapp.screening.dto.ScreeningDto;
import com.example.cinemaapp.screening.response.ScreeningResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScreeningService {

    private final ScreeningRepository screeningRepository;
    private final RoomService roomService;

    public Screening addScreening(ScreeningDto screening) {
        return screeningRepository.save(ScreeningDto
                .convertToScreening(screening, roomService.generateRoom(screening.getCinemaId())));
    }

    public void deleteScreening(UUID id) {
        var screening = screeningRepository.findById(id);
        screening.ifPresent(screeningRepository::delete);
    }

    public List<Screening> getAllScreenings() {
        return screeningRepository.findAll();
    }

    public List<Screening> getAllScreenings(UUID movieId) {
        return screeningRepository.getScreeningsByMovieId(movieId);
    }

    public Screening getScreeningByMovieId(UUID movieId) {
        return screeningRepository.getScreeningByMovieId(movieId);
    }

    public ResponseEntity<ScreeningResponseEntity> reservation(
            UUID screeningId,
            List<Room.SeatLocation> seatLocations,
            boolean cancellation) {
        Optional<Screening> screening = screeningRepository.findById(screeningId);
        if (screening.isPresent()) {
            Optional<Room> room = roomService.getRoom(screening.get().getRoomId());

            if (room.isPresent()) {
                for (Room.SeatLocation location : seatLocations) {
                    int place = location.place();
                    char row = location.row();

                    try {
                        int seatIndex = room.get().getSeats().indexOf(
                                new Room.Seat(
                                        new Room.SeatLocation(
                                                place,
                                                row),
                                        cancellation)
                        );
                        room.get().getSeats().remove(seatIndex);
                        room.get().getSeats().add(seatIndex, new Room.Seat(new Room.SeatLocation(place, row), !cancellation));
                        roomService.saveRoom(room.get());
                    } catch (Exception e) {
                        return new ResponseEntity<>(
                                new ScreeningResponseEntity(
                                        "Operation cannot be performed on " + row + " " + place + " seat"),
                                HttpStatus.BAD_REQUEST);
                    }
                }
            }
        }

        return new ResponseEntity<>(new ScreeningResponseEntity(null), HttpStatus.OK);
    }

    public List<Room.Seat> getSeats(UUID screeningId) {
        var screening = screeningRepository.findById(screeningId);
        if (screening.isPresent()) {
            Optional<Room> room = roomService.getRoom(screening.get().getRoomId());
            if (room.isPresent()) {
                return room.get().getSeats();
            }
        }
        return null;
    }
}
