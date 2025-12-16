package com.nttmk.englishlearningserver.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnsweredQuestion {
    private String questionId;

    private String selectedLabel;

    private boolean correct;
}
