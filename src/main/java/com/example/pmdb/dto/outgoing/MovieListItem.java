package com.example.pmdb.dto.outgoing;

import com.example.pmdb.domain.Movie;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovieListItem {

    private Long id;

    private String title;

    private String director;

    private Integer year;

    public MovieListItem(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.director = movie.getDirector();
        this.year = movie.getYear();
    }
}
