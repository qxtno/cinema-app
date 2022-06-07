
package com.example.cinemaapp.screening;

import java.time.ZonedDateTime;
import java.util.UUID;


public class Screening {

    private UUID id;
    private UUID cinemaId;
    private UUID roomId;
    private UUID movieId;
    private ZonedDateTime date;

    public Screening(UUID cinemaId, UUID roomId, UUID movieId, ZonedDateTime date) {
        this.cinemaId = cinemaId;
        this.roomId = roomId;
        this.movieId = movieId;
        this.date = date;
    }
}