package com.nttmk.englishlearningserver.dto;

import com.nttmk.englishlearningserver.enums.Level;
import com.nttmk.englishlearningserver.enums.WordCategory;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VocabularyDTO {

    @NotBlank
    private String word;
    private String pronunciation;
    private String meaning;
    private String example;
    private String exampleTranslation;
    private WordCategory category;
    private Level level;
}
