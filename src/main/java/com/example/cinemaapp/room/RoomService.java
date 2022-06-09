package com.example.cinemaapp.room;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public UUID generateRoom(UUID cinemaId) {

        List<Room.Seat> seats = new ArrayList<>();
        Random random = new Random();
        int roomNumber = random.nextInt(10);
        int asciiDefaultCode = 64;
        int x = random.nextInt(5, 10);
        int y = random.nextInt(5, 10);

        for (int j = 0; j < x; j++) {
            for (int k = 0; k < y; k++) {
                seats.add(
                        new Room.Seat(
                                new Room.SeatLocation(
                                        k + 1,
                                        (char) (asciiDefaultCode + (j + 1))
                                ),
                                false));
            }
        }

        UUID id = UUID.randomUUID();
        roomRepository.save(new Room(id, roomNumber, cinemaId, seats));

        return id;
    }

    public Optional<Room> getRoom(UUID roomId) {
        return roomRepository.findById(roomId);
    }

    public void saveRoom(Room room) {
        roomRepository.save(room);
    }
}
