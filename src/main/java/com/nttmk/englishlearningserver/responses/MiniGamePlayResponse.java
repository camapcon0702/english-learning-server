package com.nttmk.englishlearningserver.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MiniGamePlayResponse {
    private String id;
    private List<String> scrambledLetters;
    private String suggest;
}
