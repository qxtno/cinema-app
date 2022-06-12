package com.example.cinemaapp.room;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "room")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Room {

    @Id
    private UUID id;
    private Integer number;
    private UUID cinemaId;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
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