package com.example.pmdb.service;

import com.example.pmdb.domain.Movie;
import com.example.pmdb.dto.incoming.CreateMovieCommand;
import com.example.pmdb.dto.outgoing.MovieDetailsItem;
import com.example.pmdb.dto.outgoing.MovieListItem;
import com.example.pmdb.repository.MovieRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieListItem> getList() {
        List<Movie> movieList = movieRepository.findAll();
        return movieList.stream()
                .map(MovieListItem::new)
                .toList();
    }

    public void save(CreateMovieCommand item) {
        Movie movie = Movie
                .builder()
                .title(item.getTitle())
                .director(item.getDirector())
                .year(item.getYear())
                .genres(item.getGenres())
                .rating(item.getRating())
                .posterUrl(item.getPosterUrl())
                .build();
        movieRepository.save(movie);
    }

    public void delete(Long id) {
        Movie movie = getMovieById(id);
        if (movie != null) {
            movieRepository.delete(movie);
        } else {
            throw new EntityNotFoundException("Can't find movie with given id");
        }
    }

    public MovieDetailsItem getMovieDetails(Long id) {
        Movie movie = getMovieById(id);
        if (movie == null) {
            throw new EntityNotFoundException("Can't find movie with given id");
        }
        return new MovieDetailsItem(movie);
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }
}
