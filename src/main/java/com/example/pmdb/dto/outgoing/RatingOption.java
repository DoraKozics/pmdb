package com.example.pmdb.dto.outgoing;

import com.example.pmdb.domain.RatingType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RatingOption {

    private String name;

    private String displayName;

    public RatingOption(RatingType rating) {
        this.name = rating.name();
        this.displayName = rating.getDisplayName();
    }
}
