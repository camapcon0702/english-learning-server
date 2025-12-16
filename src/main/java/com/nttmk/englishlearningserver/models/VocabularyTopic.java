package com.nttmk.englishlearningserver.models;

import com.nttmk.englishlearningserver.enums.Level;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "vocabulary_topics")
public class VocabularyTopic {

    @Id
    private String id;

    private String name;

    private String nameEn;

    private String description;

    private String icon;

    private String color;

    private String gradientFrom;

    private String gradientTo;

    private int vocabularyCount;

    private Level level;

    @Builder.Default
    private List<Vocabulary> vocabularies = new ArrayList<>();

    private Instant createdAt;
}