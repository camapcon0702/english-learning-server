package com.nttmk.englishlearningserver.responses;

import com.nttmk.englishlearningserver.enums.TestTypeEnums;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamViewResponse {
    private String id;
    private String title;
    private String description;

    private TestTypeEnums type;
    private int duration;

    private List<QuestionViewResponse> questions;
}
