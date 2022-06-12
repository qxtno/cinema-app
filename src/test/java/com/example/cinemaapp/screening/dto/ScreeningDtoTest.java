package com.example.cinemaapp.screening.dto;

import com.example.cinemaapp.screening.Screening;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScreeningDtoTest {

    @Test
    void shouldConvertToScreening() {
        UUID cinemaId = UUID.randomUUID();
        UUID movieId = UUID.randomUUID();
        ZonedDateTime date = ZonedDateTime.now();
        ScreeningDto screeningDto = new ScreeningDto(cinemaId, movieId, date);
        UUID roomId = UUID.randomUUID();
        Screening screening = new Screening(UUID.randomUUID(), cinemaId, roomId, movieId, date);

        Screening result = ScreeningDto.convertToScreening(screeningDto, roomId);

        assertEquals(screening.getCinemaId(), result.getCinemaId());
        assertEquals(screening.getDate(), result.getDate());
        assertEquals(screening.getRoomId(), result.getRoomId());
    }
}