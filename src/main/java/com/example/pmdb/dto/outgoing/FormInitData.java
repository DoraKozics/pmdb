package com.example.pmdb.dto.outgoing;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FormInitData {

    private List<GenreOption> genreList;

    private List<RatingOption> ratingList;

    public FormInitData(List<GenreOption> genreList, List<RatingOption> ratingList) {
        this.genreList = genreList;
        this.ratingList = ratingList;
    }
}
