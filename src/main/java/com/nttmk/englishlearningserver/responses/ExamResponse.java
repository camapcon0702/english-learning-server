package com.nttmk.englishlearningserver.responses;

import com.nttmk.englishlearningserver.enums.TestTypeEnums;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamResponse {
    private String id;
    private String title;
    private String description;

    private TestTypeEnums type;
    private int duration;

    private List<QuestionResponse> questions;
}
