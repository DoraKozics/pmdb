package com.example.pmdb.dto.incoming;

import com.example.pmdb.domain.GenreType;
import com.example.pmdb.domain.RatingType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UpdateMovieCommand {

    private Long id;

    private String title;

    private String director;

    private Integer year;

    private List<GenreType> genres;

    private RatingType rating;

    private String posterUrl;
}
