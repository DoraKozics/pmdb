package com.example.pmdb.domain;

import lombok.Getter;

@Getter
public enum GenreType {
    ACTION("Action"),
    ADVENTURE("Adventure"),
    COMEDY("Comedy"),
    CRIME("Crime"),
    DRAMA("Drama"),
    FANTASY("Fantasy"),
    HORROR("Horror"),
    MYSTERY("Mystery"),
    ROMANCE("Romance"),
    SCIFI("Sci-Fi"),
    THRILLER("Thriller");

    private String displayName;

    GenreType(String displayName) {
        this.displayName = displayName;
    }
}
