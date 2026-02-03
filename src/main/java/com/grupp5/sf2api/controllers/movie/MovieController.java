package com.grupp5.sf2api.controllers.movie;

import com.grupp5.sf2api.dtos.movie.DeleteMovieDto;
import com.grupp5.sf2api.dtos.movie.RegisterMovieDto;
import com.grupp5.sf2api.dtos.movie.UpdateMovieDto;
import com.grupp5.sf2api.models.movie.Movie;
import com.grupp5.sf2api.request.movie.RegisterMovieRequest;
import com.grupp5.sf2api.request.movie.UpdateMovieRequest;
import com.grupp5.sf2api.services.movie.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/movie")
@AllArgsConstructor
public class MovieController {
    private MovieService movieService;

    @PostMapping("/register")
    public ResponseEntity<RegisterMovieDto> registerMovie(@RequestBody RegisterMovieRequest request) {
        Movie movie = new Movie(
                request.title(),
                request.durationSeconds(),
                request.description()
        );

        Movie newMovie = movieService.registerMovie(movie);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(RegisterMovieDto.from(newMovie));
    }

    @DeleteMapping("/delete/{movieId}")
    public ResponseEntity<DeleteMovieDto> deleteMovie(@PathVariable UUID movieId) {
        Movie movie = movieService.deleteMovie(movieId);

        return ResponseEntity.ok(DeleteMovieDto.from(movie));
    }

    @PutMapping("/update/{movieId}")
    public ResponseEntity<UpdateMovieDto> updateMovie(@PathVariable UUID movieId,
                                                      @RequestBody UpdateMovieRequest request) {
        Movie movie = movieService.updateMovie(movieId, request);

        return ResponseEntity.ok(UpdateMovieDto.from(movie));
    }

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }
}
