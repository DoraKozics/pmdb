package com.example.pmdb.controller;

import com.example.pmdb.dto.incoming.CreateMovieCommand;
import com.example.pmdb.dto.outgoing.*;
import com.example.pmdb.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<MovieListItem>> getMovies() {
        List<MovieListItem> movieList = movieService.getList();
        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDetailsItem> getById(@PathVariable Long id) {
        MovieDetailsItem movie = movieService.getMovieDetails(id);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createMovie(@RequestBody CreateMovieCommand command) {
        movieService.save(command);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/formData")
    public ResponseEntity<FormInitData> getFormInitData() {
        List<GenreOption> genres = movieService.getAllGenres();
        List<RatingOption> ratings = movieService.getAllRatings();
        FormInitData formData = new FormInitData(genres, ratings);
        return new ResponseEntity<>(formData, HttpStatus.OK);
    }
}
