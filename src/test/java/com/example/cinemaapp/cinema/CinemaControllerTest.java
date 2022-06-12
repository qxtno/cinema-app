package com.example.cinemaapp.cinema;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.BDDMockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CinemaController.class)
class CinemaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CinemaService cinemaService;

    @Test
    void shouldReturnAllCinemas() throws Exception {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        Cinema cinema1 = new Cinema(id1, "Lublin", null);
        Cinema cinema2 = new Cinema(id2, "Warszawa", null);

        when(cinemaService.getAllCinemas()).thenReturn(List.of(cinema1, cinema2));

        this.mockMvc.perform(get("/api/cinema/all")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").exists())
                .andExpect(jsonPath("$.[0].id").value(id1.toString()))
                .andExpect(jsonPath("$.[0].city").exists())
                .andExpect(jsonPath("$.[0].city").value("Lublin"))
                .andExpect(jsonPath("$.[1].id").exists())
                .andExpect(jsonPath("$.[1].id").value(id2.toString()))
                .andExpect(jsonPath("$.[1].city").exists())
                .andExpect(jsonPath("$.[1].city").value("Warszawa"));
    }

    @Test
    void shouldReturnCinemaById() throws Exception {
        UUID id = UUID.randomUUID();
        Cinema cinema = new Cinema(id, "Lublin", null);

        when(cinemaService.getCinemaById(id)).thenReturn(Optional.of(cinema));

        this.mockMvc.perform(get("/api/cinema").param("cinemaId", String.valueOf(id)))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.city").exists())
                .andExpect(jsonPath("$.city").value("Lublin"));
    }

    @Test
    void shouldAddCinema() throws Exception {
        UUID id = UUID.randomUUID();
        Cinema cinema = new Cinema(id, "Lublin", null);

        when(cinemaService.addCinema("Lublin")).thenReturn(cinema);

        this.mockMvc.perform(post("/api/cinema").param("city", "Lublin"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.city").exists())
                .andExpect(jsonPath("$.city").value("Lublin"));
    }
}