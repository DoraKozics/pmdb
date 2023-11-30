package com.example.pmdb.service;

import com.example.pmdb.domain.Movie;
import com.example.pmdb.dto.incoming.CreateMovieCommand;
import com.example.pmdb.dto.outgoing.MovieListItem;
import com.example.pmdb.repository.MovieRepository;
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
}
