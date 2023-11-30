package com.example.pmdb.controller;

import com.example.pmdb.dto.incoming.CreateMovieCommand;
import com.example.pmdb.dto.outgoing.MovieListItem;
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

    @PostMapping
    public ResponseEntity<Void> createMovie(@RequestBody CreateMovieCommand command) {
        movieService.save(command);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
