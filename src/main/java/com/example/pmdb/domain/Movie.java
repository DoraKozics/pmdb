package com.example.pmdb.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movie")
@Builder
@Getter
@Setter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "director")
    private String director;

    @Column(name = "year")
    private Integer year;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = GenreType.class)
    @CollectionTable(name = "movie_genre")
    private List<GenreType> genres = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "rating")
    private RatingType rating;

    @Column(name = "poster_url")
    private String posterUrl;

//    public Movie() {
//    }
}
