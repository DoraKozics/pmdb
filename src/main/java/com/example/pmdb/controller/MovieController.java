package com.example.pmdb.controller;

import com.example.pmdb.dto.incoming.CreateMovieCommand;
import com.example.pmdb.dto.incoming.UpdateMovieCommand;
import com.example.pmdb.dto.outgoing.*;
import com.example.pmdb.service.MovieService;
import com.example.pmdb.validator.CreateMovieCommandValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    private final CreateMovieCommandValidator validator;

    @Autowired
    public MovieController(MovieService movieService,
                           CreateMovieCommandValidator createMovieCommandValidator) {
        this.movieService = movieService;
        this.validator = createMovieCommandValidator;
    }

    @InitBinder("createMovieCommand")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
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
    public ResponseEntity<Void> createMovie(@RequestBody @Valid CreateMovieCommand command) {
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

    @PutMapping
    public ResponseEntity<Void> updateMovie(@RequestBody UpdateMovieCommand command) {
        movieService.update(command);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
