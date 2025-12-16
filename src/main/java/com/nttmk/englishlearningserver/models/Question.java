package com.nttmk.englishlearningserver.models;

import com.nttmk.englishlearningserver.enums.TestTypeEnums;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "questions")
public class Question {
    @Id
    private String id;

    private String title;
    private TestTypeEnums type;

    private String content;

    private String audioUrl;

    private List<Answer> answers;
}
