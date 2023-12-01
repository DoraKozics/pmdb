package com.example.pmdb.dto.outgoing;

import com.example.pmdb.domain.GenreType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GenreOption {

    private String name;

    private String displayName;

    public GenreOption(GenreType genre) {
        this.name = genre.name();
        this.displayName = genre.getDisplayName();
    }
}
