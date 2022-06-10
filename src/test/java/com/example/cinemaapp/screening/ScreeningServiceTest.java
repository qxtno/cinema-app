package com.example.cinemaapp.screening;

import com.example.cinemaapp.room.Room;
import com.example.cinemaapp.screening.response.ScreeningResponseEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ScreeningServiceTest {

    @Mock
    private ScreeningRepository screeningRepository;

    @InjectMocks
    private ScreeningService screeningService;

    @Test
    void shouldReturnAllScreenings() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        UUID cinemaId1 = UUID.randomUUID();
        UUID cinemaId2 = UUID.randomUUID();
        UUID roomId1 = UUID.randomUUID();
        UUID roomId2 = UUID.randomUUID();
        UUID movieId1 = UUID.randomUUID();
        UUID movieId2 = UUID.randomUUID();
        Screening screening1 = new Screening(id1, cinemaId1, roomId1, movieId1, ZonedDateTime.now());
        Screening screening2 = new Screening(id2, cinemaId2, roomId2, movieId2, ZonedDateTime.now());

        when(screeningRepository.findAll()).thenReturn(List.of(screening1, screening2));
        List<Screening> result = screeningService.getAllScreenings();

        assertEquals(List.of(screening1, screening2), result);
    }

    @Test
    void shouldReturnAllScreeningsByMovieId() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        UUID cinemaId1 = UUID.randomUUID();
        UUID cinemaId2 = UUID.randomUUID();
        UUID roomId1 = UUID.randomUUID();
        UUID roomId2 = UUID.randomUUID();
        UUID movieId = UUID.randomUUID();
        Screening screening1 = new Screening(id1, cinemaId1, roomId1, movieId, ZonedDateTime.now());
        Screening screening2 = new Screening(id2, cinemaId2, roomId2, movieId, ZonedDateTime.now());

        when(screeningRepository.findAll()).thenReturn(List.of(screening1, screening2));
        List<Screening> result = screeningService.getAllScreenings();

        assertEquals(List.of(screening1, screening2), result);
    }

    @Test
    void shouldMakeReservation() {
        List<Room.SeatLocation> locations = List.of(
                new Room.SeatLocation(1, 'A'),
                new Room.SeatLocation(2, 'A')
        );
        UUID screeningId = UUID.randomUUID();

        ResponseEntity<ScreeningResponseEntity> result = screeningService.reservation(screeningId, locations, false);

        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void shouldCancelReservation() {
        List<Room.SeatLocation> locations = List.of(
                new Room.SeatLocation(1, 'A'),
                new Room.SeatLocation(2, 'A')
        );
        UUID screeningId = UUID.randomUUID();

        ResponseEntity<ScreeningResponseEntity> result = screeningService.reservation(screeningId, locations, true);

        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }
}