package com.nttmk.englishlearningserver.models;

import com.nttmk.englishlearningserver.enums.Level;
import com.nttmk.englishlearningserver.enums.WordCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vocabulary {

    private String id;

    private String word;

    private String pronunciation;

    private String meaning;

    private String example;

    private String exampleTranslation;

    private WordCategory category;

    private Level level;

    private String topic;

    private Instant createdAt;
}
