package com.nttmk.englishlearningserver.dto;

import com.nttmk.englishlearningserver.enums.Level;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VocabularyTopicDTO {
    private String name;
    private String nameEn;
    private String description;
    private String icon;
    private String color;
    private String gradientFrom;
    private String gradientTo;
    private Level level;
}
