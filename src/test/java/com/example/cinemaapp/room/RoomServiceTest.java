package com.example.cinemaapp.room;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomService roomService;

    @Test
    void shouldGenerateRoom() {
        UUID id = UUID.randomUUID();
        UUID cinemaId = UUID.randomUUID();
        Room room = new Room(id, 1, cinemaId, List.of(
                new Room.Seat(
                        new Room.SeatLocation(1, 'A'),
                        false
                ),
                new Room.Seat(
                        new Room.SeatLocation(2, 'A'),
                        false
                )));

        when(roomRepository.save(any())).thenReturn(room);
        UUID result = roomService.generateRoom(cinemaId);

        assertNotNull(result);
        verify(roomRepository, times(1)).save(any());
    }

    @Test
    void shouldReturnRoomById() {
        UUID id = UUID.randomUUID();
        Room room = new Room(id, 1, UUID.randomUUID(), null);

        when(roomRepository.findById(id)).thenReturn(Optional.of(room));
        Optional<Room> result = roomService.getRoom(id);

        assertEquals(room, result.get());
        verify(roomRepository, times(1)).findById(id);
    }
}