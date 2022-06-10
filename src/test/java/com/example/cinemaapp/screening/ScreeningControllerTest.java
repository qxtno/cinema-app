package com.example.cinemaapp.screening;

import com.example.cinemaapp.room.Room;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ScreeningController.class)
class ScreeningControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScreeningService screeningService;

    @Test
    void shouldDeleteScreening() throws Exception {
        UUID screeningId = UUID.randomUUID();

        doNothing().when(screeningService).deleteScreening(screeningId);
        this.mockMvc.perform(delete("/api/screening").param("id", String.valueOf(screeningId)))
                .andExpect(status().isOk());

        verify(screeningService, times(1)).deleteScreening(screeningId);
    }

    @Test
    void shouldReturnAllScreenings() throws Exception {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        UUID cinemaId1 = UUID.randomUUID();
        UUID cinemaId2 = UUID.randomUUID();
        UUID roomId1 = UUID.randomUUID();
        UUID roomId2 = UUID.randomUUID();
        UUID movieId1 = UUID.randomUUID();
        UUID movieId2 = UUID.randomUUID();
        List<Screening> screenings = List.of(
                new Screening(id1, cinemaId1, roomId1, movieId1, ZonedDateTime.now()),
                new Screening(id2, cinemaId2, roomId2, movieId2, ZonedDateTime.now())
        );

        when(screeningService.getAllScreenings()).thenReturn(screenings);

        this.mockMvc.perform(get("/api/screening/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").exists())
                .andExpect(jsonPath("$.[0].id").value(id1.toString()))
                .andExpect(jsonPath("$.[0].cinemaId").exists())
                .andExpect(jsonPath("$.[0].cinemaId").value(cinemaId1.toString()))
                .andExpect(jsonPath("$.[0].roomId").exists())
                .andExpect(jsonPath("$.[0].roomId").value(roomId1.toString()))
                .andExpect(jsonPath("$.[0].movieId").exists())
                .andExpect(jsonPath("$.[0].movieId").value(movieId1.toString()))
                .andExpect(jsonPath("$.[1].id").exists())
                .andExpect(jsonPath("$.[1].id").value(id2.toString()))
                .andExpect(jsonPath("$.[1].cinemaId").exists())
                .andExpect(jsonPath("$.[1].cinemaId").value(cinemaId2.toString()))
                .andExpect(jsonPath("$.[1].roomId").exists())
                .andExpect(jsonPath("$.[1].roomId").value(roomId2.toString()))
                .andExpect(jsonPath("$.[1].movieId").exists())
                .andExpect(jsonPath("$.[1].movieId").value(movieId2.toString()));
    }

    @Test
    void shouldReturnAllScreeningsByMovieId() throws Exception {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        UUID cinemaId1 = UUID.randomUUID();
        UUID cinemaId2 = UUID.randomUUID();
        UUID roomId1 = UUID.randomUUID();
        UUID roomId2 = UUID.randomUUID();
        UUID movieId = UUID.randomUUID();
        List<Screening> screenings = List.of(
                new Screening(id1, cinemaId1, roomId1, movieId, ZonedDateTime.now()),
                new Screening(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID(), ZonedDateTime.now()),
                new Screening(id2, cinemaId2, roomId2, movieId, ZonedDateTime.now())
        );

        when(screeningService.getAllScreenings(movieId)).thenReturn(List.of(screenings.get(0), screenings.get(2)));

        this.mockMvc.perform(get("/api/screening/movie/all").param("movieId", String.valueOf(movieId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").exists())
                .andExpect(jsonPath("$.[0].id").value(id1.toString()))
                .andExpect(jsonPath("$.[0].cinemaId").exists())
                .andExpect(jsonPath("$.[0].cinemaId").value(cinemaId1.toString()))
                .andExpect(jsonPath("$.[0].roomId").exists())
                .andExpect(jsonPath("$.[0].roomId").value(roomId1.toString()))
                .andExpect(jsonPath("$.[0].movieId").exists())
                .andExpect(jsonPath("$.[0].movieId").value(movieId.toString()))
                .andExpect(jsonPath("$.[1].id").exists())
                .andExpect(jsonPath("$.[1].id").value(id2.toString()))
                .andExpect(jsonPath("$.[1].cinemaId").exists())
                .andExpect(jsonPath("$.[1].cinemaId").value(cinemaId2.toString()))
                .andExpect(jsonPath("$.[1].roomId").exists())
                .andExpect(jsonPath("$.[1].roomId").value(roomId2.toString()))
                .andExpect(jsonPath("$.[1].movieId").exists())
                .andExpect(jsonPath("$.[1].movieId").value(movieId.toString()));
    }

    @Test
    void shouldReturnSeats() throws Exception {
        UUID screeningId = UUID.randomUUID();
        List<Room.Seat> seats = List.of(
                new Room.Seat(new Room.SeatLocation(1, 'A'), false),
                new Room.Seat(new Room.SeatLocation(2, 'A'), false));

        when(screeningService.getSeats(any())).thenReturn(seats);

        this.mockMvc.perform(get("/api/screening/movie/seats").param("screeningId", String.valueOf(screeningId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].location").exists())
                .andExpect(jsonPath("$.[1].location").exists());
    }
}