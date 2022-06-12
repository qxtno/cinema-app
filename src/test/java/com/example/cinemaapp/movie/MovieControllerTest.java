package com.example.cinemaapp.movie;

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

import static org.mockito.BDDMockito.doNothing;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MovieController.class)
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @Test
    void shouldAddMovie() throws Exception {
        String[] movies = new String[]{"Name", "Name 2"};

        doNothing().when(movieService).saveAllMovies(movies);

        this.mockMvc.perform(post("/api/movie")
                        .param("names", "Name")
                        .param("names", "Name 2"))
                .andDo(print()).andExpect(status().isOk());
        verify(movieService, times(1)).saveAllMovies(movies);
    }

    @Test
    void shouldReturnAllMovies() throws Exception {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        List<Movie> movies = List.of(
                new Movie(id1, "Name", null),
                new Movie(id2, "Name 2", null));

        when(movieService.getAll()).thenReturn(movies);

        this.mockMvc.perform(get("/api/movie/all"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").exists())
                .andExpect(jsonPath("$.[0].id").value(id1.toString()))
                .andExpect(jsonPath("$.[0].name").exists())
                .andExpect(jsonPath("$.[0].name").value("Name"))
                .andExpect(jsonPath("$.[1].id").exists())
                .andExpect(jsonPath("$.[1].id").value(id2.toString()))
                .andExpect(jsonPath("$.[1].name").exists())
                .andExpect(jsonPath("$.[1].name").value("Name 2"));
    }

    @Test
    void shouldReturnMovieById() throws Exception {
        UUID id = UUID.randomUUID();
        Movie movie = new Movie(id, "Name", null);

        when(movieService.getMovieById(id)).thenReturn(Optional.of(movie));

        this.mockMvc.perform(get("/api/movie").param("id", String.valueOf(id)))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.name").value("Name"));
    }

    @Test
    void shouldDeleteMovie() throws Exception {
        UUID id = UUID.randomUUID();

        doNothing().when(movieService).delete(id);

        this.mockMvc.perform(delete("/api/movie").param("id", String.valueOf(id)))
                .andDo(print()).andExpect(status().isOk());
        verify(movieService, times(1)).delete(id);
    }
}