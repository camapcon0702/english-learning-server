package com.nttmk.englishlearningserver.responses;

import com.nttmk.englishlearningserver.enums.TestTypeEnums;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionResponse {
    private String id;
    private String title;
    private TestTypeEnums type;
    private String content;
    private String audioUrl;

    private List<AnswerResponse> answers;
}
