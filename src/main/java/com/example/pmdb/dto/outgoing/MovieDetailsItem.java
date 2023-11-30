package com.example.pmdb.dto.outgoing;

import com.example.pmdb.domain.GenreType;
import com.example.pmdb.domain.Movie;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MovieDetailsItem {

    private Long id;

    private String title;

    private String director;

    private Integer year;

    private List<String> genres;

    private String rating;

    private String posterUrl;

    public MovieDetailsItem(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.director = movie.getDirector();
        this.year = movie.getYear();
        this.genres = movie.getGenres().stream().map(GenreType::getDisplayName).toList();
        this.rating = movie.getRating().getDisplayName();
        this.posterUrl = movie.getPosterUrl();
    }
}
