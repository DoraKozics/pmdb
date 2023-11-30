package com.example.pmdb.domain;

import lombok.Getter;

@Getter
public enum RatingType {
    G("G - General Audiences"),
    PG("PG - Parental Guidance Suggested"),
    PG_13("PG-13 - Parents Strongly Cautioned"),
    R("R - Restricted"),
    NC_17("NC-17 - Adults Only");

    private String displayName;

    RatingType(String displayName) {
        this.displayName = displayName;
    }
}
