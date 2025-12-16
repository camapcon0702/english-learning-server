package com.nttmk.englishlearningserver.models;

import com.nttmk.englishlearningserver.enums.TestTypeEnums;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "exams")
@Builder
public class Exam {
    @Id
    private String id;

    private String title;
    private String description;

    private TestTypeEnums type;

    private int duration;

    private List<String> questionIds;

    private LocalDateTime createdAt;
}
