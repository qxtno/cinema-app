package com.example.cinemaapp.room;


import javax.persistence.Id;
import java.util.List;
import java.util.UUID;

public class Room {
    @Id
    private UUID id;
    private Integer number;
    private UUID cinemaId;
    private List<Seat> seats;

    public record Seat(
            SeatLocation location,
            boolean occupied
    ) {
    }

    public record SeatLocation(
            Integer place,
            char row
    ) {
    }
}