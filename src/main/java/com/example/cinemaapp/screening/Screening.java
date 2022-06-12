
package com.example.cinemaapp.screening;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "screening")
public class Screening {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
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