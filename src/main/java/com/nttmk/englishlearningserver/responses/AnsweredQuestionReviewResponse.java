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
public class AnsweredQuestionReviewResponse {
    private String questionId;
    private String title;
    private String content;
    private String audioUrl;

    private String selectedLabel;
    private String correctLabel;
    private boolean correct;

    private List<AnswerViewResponse> answers;
}


