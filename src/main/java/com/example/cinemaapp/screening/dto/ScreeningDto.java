package com.example.cinemaapp.screening.dto;

import com.example.cinemaapp.screening.Screening;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ScreeningDto {
    private UUID cinemaId;
    private UUID movieId;
    private ZonedDateTime date;

    public static Screening convertToScreening(ScreeningDto screeningDto, UUID roomId) {
        return new Screening(screeningDto.getCinemaId(), roomId, screeningDto.getMovieId(), screeningDto.getDate());
    }
}