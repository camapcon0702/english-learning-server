package com.nttmk.englishlearningserver.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamResultResponse {
    private String examId;
    private int totalQuestions;
    private int correctAnswers;
    private double score;
}
