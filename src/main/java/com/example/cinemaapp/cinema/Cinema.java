package com.example.cinemaapp.cinema;

import com.example.cinemaapp.room.Room;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cinema")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Cinema {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private String city;

    @JsonIgnore
    @Transient
    private List<Room> rooms;

    public Cinema(String city) {
        this.city = city;
    }
}